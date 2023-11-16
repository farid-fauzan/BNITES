package com.bni.rest.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LayananPojo {

    private String namaLayanan;

    private Double hargaPertemuan;

    private String jadwal;

    private Long jumlahPertemuan;
}
