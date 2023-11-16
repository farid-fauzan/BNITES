package com.bni.rest.respository;

import com.bni.rest.entity.Layanan;
import com.bni.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LayananRepository extends JpaRepository <Layanan, Long> {

}
