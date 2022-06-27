package com.ibme.pacs.entities.HoSoBenhAn.NoiTru;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class TinhTrangRaVien extends KhamBenhNoiTru {
    private String ketQuaDieuTri;
    private String giaiPhauBenh;
    private String thoiGianTuVong;
    private String nguyenNhanTuVong;
    private String khamNghiemTuThi;
    private String chuanDoanGiaiPhauTuThi;
}
