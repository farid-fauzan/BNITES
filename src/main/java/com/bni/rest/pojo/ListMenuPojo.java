package com.bni.rest.pojo;

import com.bni.rest.entity.Latihan;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ListMenuPojo {

    private String namaLayanan;

    private Double hargaPertemuan;

    private String jadwal;

    private Long jumlahPertemuan;

    private List<ListLatihanPojo> listLatihanPojos;


}
