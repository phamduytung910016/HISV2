package com.ibme.pacs.dto;

import com.ibme.pacs.entities.Department;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.HoSoBenhAn.DoiTuong;
import com.ibme.pacs.entities.HoSoBenhAn.TrangThai;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class HoSoBenhAnNgoaiTruDTO {
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
    private String benhLy;
    private String quaTrinhBenhLy;


    //Khám bệnh ngoại trú
    @NotEmpty(message = "Chưa nhập")
    private String toanThan;

    @NotEmpty(message = "Chưa nhập")
    private String cacBoPhan;

    @NotEmpty(message = "Chưa nhập")
    private String tomTatKetQuaCanlamSang;

    @NotEmpty(message = "Chưa nhập")
    private String chuanDoanBanDau;

    @NotEmpty(message = "Chưa nhập")
    private String daXuLy;

    @NotEmpty(message = "Chưa nhập")
    private String chuanDoanKhiRaVien;

    @NotEmpty(message = "Chưa nhập")
    private String dieuTriNgoaiTru;


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
