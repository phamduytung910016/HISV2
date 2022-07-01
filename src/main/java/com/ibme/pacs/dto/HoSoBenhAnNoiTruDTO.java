package com.ibme.pacs.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class HoSoBenhAnNoiTruDTO {
    private Integer id;

    @NotEmpty(message = "Chưa nhập")
    private String soYTe;

    @NotEmpty(message = "Chưa nhập")
    private String benhVien;


    @NotNull(message = "Chưa nhập")
    private Long khoaPhongId;

    @NotEmpty(message = "Chưa nhập")
    private String soLuuTru;

    @NotEmpty(message = "Chưa nhập")
    private String bacSiLamBenhAn;

    @NotNull(message = "Chưa nhập")
    private Long nhanVienLuTruId;

    @NotEmpty(message = "Chưa nhập")
    private String trangThai;


    //Thông tin hành chính
    @NotEmpty(message = "Chưa nhập")
    private String hoVaTen;

    @NotNull(message = "Chưa nhập")
    private Long tuoi;

    @NotEmpty(message = "Chưa nhập")
    private String ngaySinh;

    @NotEmpty(message = "Chưa nhập")
    private String gioiTinh;

    @NotEmpty(message = "Chưa nhập")
    private String ngheNghiep;

    @NotEmpty(message = "Chưa nhập")
    private String danToc;

    @NotEmpty(message = "Chưa nhập")
    private String quocTich;

    @NotEmpty(message = "Chưa nhập")
    private String diaChi;

    @NotEmpty(message = "Chưa nhập")
    private String noiLamViec;

    @NotEmpty(message = "Chưa nhập")
    private String doiTuong;
    private String baoHiemYTeCoGiaTri;
    private String soTheBaoHiemYTe;

    @NotEmpty(message = "Chưa nhập")
    private String hovaTenNguoiNha;

    @NotEmpty(message = "Chưa nhập")
    private String soDienThoai;


    //Tiểu sử bệnh
    @NotEmpty(message = "Chưa nhập")
    private String benhLy;

    @NotEmpty(message = "Chưa nhập")
    private String quaTrinhBenhLy;


    //Quản lý người bệnh;
    @NotEmpty(message = "Chưa nhập")
    private String thoiGianVaoVien;

    @NotEmpty(message = "Chưa nhập")
    private String trucTiepVao;

    @NotEmpty(message = "Chưa nhập")
    private String noiGioiThieu;

    @NotEmpty(message = "Chưa nhập")
    private String vaoKhoa;

    @NotEmpty(message = "Chưa nhập")
    private String chuyenVien;

    @NotEmpty(message = "Chưa nhập")
    private String chuyenDen;

    @NotEmpty(message = "Chưa nhập")
    private String raVien;

    @NotNull(message = "Chưa nhập")
    private Integer soNgayDieuTri;


    //Khám bệnh nội trú
    @NotEmpty(message = "Chưa nhập")
    private String tuanHoan;

    @NotEmpty(message = "Chưa nhập")
    private String hoHap;

    @NotEmpty(message = "Chưa nhập")
    private String tieuHoa;

    @NotEmpty(message = "Chưa nhập")
    private String thanTietNieuSinhDuc;

    @NotEmpty(message = "Chưa nhập")
    private String thanKinh;

    @NotEmpty(message = "Chưa nhập")
    private String coXuongKhop;

    @NotEmpty(message = "Chưa nhập")
    private String taiMuiHong;

    @NotEmpty(message = "Chưa nhập")
    private String rangHamMat;

    @NotEmpty(message = "Chưa nhập")
    private String mat;

    @NotEmpty(message = "Chưa nhập")
    private String xetNghiemCanLamSang;

    @NotEmpty(message = "Chưa nhập")
    private String tomTatBenhAn;


    //Tình trạng ra viện
    @NotEmpty(message = "Chưa nhập")
    private String ketQuaDieuTri;

    @NotEmpty(message = "Chưa nhập")
    private String giaiPhauBenh;

    @NotEmpty(message = "Chưa nhập")
    private String thoiGianTuVong;

    @NotEmpty(message = "Chưa nhập")
    private String nguyenNhanTuVong;

    @NotEmpty(message = "Chưa nhập")
    private String khamNghiemTuThi;

    @NotEmpty(message = "Chưa nhập")
    private String chuanDoanGiaiPhauTuThi;


    //Tổng kết bệnh án
    @NotEmpty(message = "Chưa nhập")
    private String quaTrinhBenhlyVaDienBienLamSang;

    @NotEmpty(message = "Chưa nhập")
    private String tomTatKetQuaXetNghiemCanLamSang;

    @NotEmpty(message = "Chưa nhập")
    private String phuongPhapDieuTri;

    @NotEmpty(message = "Chưa nhập")
    private String tinhTrangNguoiBenhRaVien;

    @NotEmpty(message = "Chưa nhập")
    private String huongDieuTriVaCacCheDoTiepTheo;
}
