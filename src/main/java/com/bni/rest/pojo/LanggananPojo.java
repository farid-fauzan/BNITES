package com.bni.rest.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Setter
@Getter
public class LanggananPojo {

    private Long idUser;

    private Long idLayanan;

    private Integer sisaPertemuan;

}
