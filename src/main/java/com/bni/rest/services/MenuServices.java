package com.bni.rest.services;


import com.bni.rest.entity.Latihan;
import com.bni.rest.entity.Layanan;
import com.bni.rest.entity.OtpUser;
import com.bni.rest.entity.User;
import com.bni.rest.pojo.*;
import com.bni.rest.respository.LatihanRepository;
import com.bni.rest.respository.LayananRepository;
import com.bni.rest.respository.OtpUserRepository;
import com.bni.rest.respository.UserRepository;
import com.bni.rest.utility.JwtUtil;
import com.bni.rest.utility.MessageModel;
import com.bni.rest.utility.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

@Service
public class MenuServices {

    @Autowired
    private LayananRepository layananRepository;

    @Autowired
    private LatihanRepository latihanRepository;

    public ResponseEntity addLayanan(LayananPojo layananPojo) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try {
            Layanan layanan = new Layanan();
            layanan.setNamaLayanan(layananPojo.getNamaLayanan());
            layanan.setHargaPertemuan(layananPojo.getHargaPertemuan());
            layanan.setJumlahPertemuan(layananPojo.getJumlahPertemuan());
            layanan.setJadwal(layananPojo.getJadwal());
            layananRepository.save(layanan);

            msg.setStatus(true);
            msg.setData(null);
            msg.setMessage("Berhasil Tambah Layanan");
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity addLatihan(LatihanPojo latihanPojo) {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try {
            Latihan latihan = new Latihan();
            latihan.setNamaLatihan(latihanPojo.getNamaLatihan());
            latihan.setDurasiLatihan(latihanPojo.getDurasiLatihan());
            latihan.setKeterangan(latihanPojo.getKeterangan());
            latihanRepository.save(latihan);

            msg.setStatus(true);
            msg.setData(null);
            msg.setMessage("Berhasil Tambah Latihan");
            return ResponseEntity.status(HttpStatus.CREATED).body(msg);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    public ResponseEntity listLayanan() {
        Map<String, Object> result = new HashMap<>();
        MessageModel msg = new MessageModel();
        try {
            List<Layanan> layanans = layananRepository.findAll();
            ListMenuPojo listMenuPojo = new ListMenuPojo();

            for (int i = 0;i<layanans.size();i++){
                listMenuPojo.setNamaLayanan(layanans.get(i).getNamaLayanan());
                listMenuPojo.setJadwal(layanans.get(i).getJadwal());
                listMenuPojo.setHargaPertemuan(layanans.get(i).getHargaPertemuan());
                listMenuPojo.setJumlahPertemuan(layanans.get(i).getJumlahPertemuan());

                List<Latihan> latihans = latihanRepository.findByIdLayanan(layanans.get(i).getIdLayanan());
                ListLatihanPojo listLatihanPojo = new ListLatihanPojo();
                List<ListLatihanPojo> listLatihanPojos = new ArrayList<>();
                for (int j = 0;j<latihans.size();j++){
                    listLatihanPojo.setNamaLatihan(latihans.get(j).getNamaLatihan());
                    listLatihanPojo.setDurasiLatihan(latihans.get(j).getDurasiLatihan());
                    listLatihanPojo.setKeterangan(latihans.get(j).getKeterangan());
                    listLatihanPojos.add(listLatihanPojo);
                }
                listMenuPojo.setListLatihanPojos(listLatihanPojos);
            }

            if (layanans != null){
                msg.setStatus(true);
                msg.setData(listMenuPojo);
                msg.setMessage("Menu Tersedia!");
                return ResponseEntity.ok().body(msg);

            }else {
                msg.setStatus(true);
                msg.setData(null);
                msg.setMessage("Tidak Dapat menemukan data");
                return ResponseEntity.ok().body(msg);
            }

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
