package com.bni.rest.respository;

import com.bni.rest.entity.CardInfo;
import com.bni.rest.entity.Langganan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository <CardInfo, Long> {

}
