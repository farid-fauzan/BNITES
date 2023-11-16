package com.bni.rest.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Setter
@Getter
public class LatihanPojo {

    private String namaLatihan;
    private Long durasiLatihan;
    private String keterangan;
}
