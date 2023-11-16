package com.bni.rest.respository;

import com.bni.rest.entity.Latihan;
import com.bni.rest.entity.Layanan;
import com.bni.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LatihanRepository extends JpaRepository <Latihan, Long> {
    @Query(value = "SELECT * FROM latihan WHERE id_layanan=:idLayanan" , nativeQuery = true)
    List<Latihan> findByIdLayanan(@Param("idLayanan") Long idLayanan);
}
