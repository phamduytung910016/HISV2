package com.ibme.pacs.entities.HoSoBenhAn;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class TieuSuBenh extends TongKetBenhAn{
    private String benhLy;
    private String quaTrinhBenhLy;
}
