package com.ibme.pacs.entities.HoSoBenhAn.NoiTru;

import lombok.Data;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class QuanLyNguoiBenh extends TinhTrangRaVien{
    private String thoiGianVaoVien;
    private String trucTiepVao;
    private String noiGioiThieu;
    private String vaoKhoa;
    private String chuyenVien;
    private String chuyenDen;
    private String raVien;
    private int soNgayDieuTri;
}
