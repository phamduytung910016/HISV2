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


@Data
public class HoSoBenhAnNgoaiTruDTO {
    private Integer id;
    private String soYTe;
    private String benhVien;
    private int khoaPhongId;
    private String soLuuTru;
    private String bacSiLamBenhAn;
    private int nhanVienLuTruId;
    private String trangThai;

    //Thông tin hành chính
    private String hoVaTen;
    private int tuoi;
    private String ngaySinh;
    private boolean gioiTinh;
    private String ngheNghiep;
    private String danToc;
    private String quocTich;
    private String diaChi;
    private String noiLamViec;
    private String doiTuong;
    private String baoHiemYTeCoGiaTri;
    private String soTheBaoHiemYTe;
    private String hovaTenNguoiNha;
    private String soDienThoai;

    //Tiểu sử bệnh
    private String benhLy;
    private String quaTrinhBenhLy;

    //Khám bệnh
    private String toanThan;
    private String cacBoPhan;
    private String tomTatKetQuaCanlamSang;
    private String chuanDoanBanDau;
    private String daXuLy;
    private String chuanDoanKhiRaVien;
    private String dieuTriNgoaiTru;


    //Tổng kết bệnh án
    private String quaTrinhBenhlyVaDienBienLamSang;
    private String tomTatKetQuaXetNghiemCanLamSang;
    private String phuongPhapDieuTri;
    private String tinhTrangNguoiBenhRaVien;
    private String huongDieuTriVaCacCheDoTiepTheo;
}
