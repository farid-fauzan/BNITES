package com.bni.rest.respository;

import com.bni.rest.entity.OtpUser;
import com.bni.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OtpUserRepository extends JpaRepository <OtpUser, Long> {
    @Query(value = "SELECT * FROM otp_user WHERE otp_user.email =:email and " +
            "otp_user.otp =:otp and otp_user.tgl_berlaku > :tglBerlaku and is_valid = 1" , nativeQuery = true)
    OtpUser findOtp(@Param("email") String email, @Param("otp") String otp, @Param("tglBerlaku") Date tglBerlaku);

}
