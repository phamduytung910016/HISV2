package com.ibme.pacs.entities.HoSoBenhAn;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public class TongKetBenhAn {
    private String quaTrinhBenhlyVaDienBienLamSang;
    private String tomTatKetQuaXetNghiemCanLamSang;
    private String phuongPhapDieuTri;
    private String tinhTrangNguoiBenhRaVien;
    private String huongDieuTriVaCacCheDoTiepTheo;

}
