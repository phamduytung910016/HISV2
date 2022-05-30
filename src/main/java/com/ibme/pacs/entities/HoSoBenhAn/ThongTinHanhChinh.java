package com.ibme.pacs.entities.HoSoBenhAn;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@Data
public class ThongTinHanhChinh extends TieuSuBenh {
    private String hoVaTen;
    private int tuoi;
    private String ngaySinh;
    private boolean gioiTinh;
    private String ngheNghiep;
    private String danToc;
    private String quocTich;
    private String diaChi;
    private String noiLamViec;
    @Enumerated(EnumType.STRING)
    private DoiTuong doiTuong;
    private String baoHiemYTeCoGiaTri;
    private String soTheBaoHiemYTe;
    private String hovaTenNguoiNha;
    private String soDienThoai;
}
