package com.bni.rest.services;


import com.bni.rest.entity.Langganan;
import com.bni.rest.entity.Layanan;
import com.bni.rest.entity.OtpUser;
import com.bni.rest.entity.User;
import com.bni.rest.pojo.LanggananPojo;
import com.bni.rest.respository.LanggananRepository;
import com.bni.rest.respository.LayananRepository;
import com.bni.rest.respository.OtpUserRepository;
import com.bni.rest.respository.UserRepository;
import com.bni.rest.utility.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PesananServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LanggananRepository langgananRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private OtpUserRepository otpUserRepository;

    public ResponseEntity verif (Long idLangganan) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try {

            Langganan langganan = langgananRepository.findLangganan(idLangganan);
            User user = userRepository.getById(langganan.getIdUser());

            if (user != null) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.DAY_OF_MONTH, 1); // Menambah 1 hari

                String otp = userServices.generateOTP();

                OtpUser otpUser = new OtpUser();
                otpUser.setEmail(user.getEmail());
                otpUser.setOtp(otp);
                otpUser.setTglBerlaku(calendar.getTime());
                otpUser.setValid(true);


                emailService.sendEmail(otpUser.getEmail(), "Konfirmasi Pesanan", "Kode OTP " +
                        "untuk pesanan : "+otp+" " +
                        "dapat digunakan dalam 24 jam");
                otpUserRepository.save(otpUser);

                msg.setStatus(true);
                msg.setMessage("Cek Email untuk mendapatkan OTP");
                msg.setData(null);
                return ResponseEntity.ok().body(msg);
            }else {
                msg.setStatus(false);
                msg.setMessage("Request Reset Password Gagal");
                msg.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity konfirmasi (Long idLangganan, String otp) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try {
            Langganan langganan = langgananRepository.findLangganan(idLangganan);
            User user = userRepository.getById(langganan.getIdUser());

            OtpUser otpUser = otpUserRepository.findOtp(user.getEmail(), otp, new Date());

            if (otpUser != null) {
                langganan.setValid(true);
                langgananRepository.save(langganan);

                otpUser.setValid(false);
                otpUserRepository.save(otpUser);

                msg.setStatus(true);
                msg.setMessage("Pembayaran Telah Dikonfirmasi");
                msg.setData(null);
                return ResponseEntity.ok().body(msg);
            }else {
                msg.setStatus(false);
                msg.setMessage("Pembayaran Tidak Dikonfirmasi");
                msg.setData(null);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
