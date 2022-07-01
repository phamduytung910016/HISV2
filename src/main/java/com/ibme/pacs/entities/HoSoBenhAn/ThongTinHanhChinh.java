package com.ibme.pacs.entities.HoSoBenhAn;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class ThongTinHanhChinh extends TieuSuBenh {
    @Column(name = "hoVaTen", length = 255)
    private String hoVaTen;

    @Column(name = "tuoi", length = 25)
    private Long tuoi;

    private String ngaySinh;

    @Column(name = "gioiTinh", length = 255)
    private String gioiTinh;


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
}
