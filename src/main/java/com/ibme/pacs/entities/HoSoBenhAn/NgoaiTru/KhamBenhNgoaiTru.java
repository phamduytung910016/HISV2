package com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru;

import com.ibme.pacs.entities.HoSoBenhAn.HoSoBenhAn;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class KhamBenhNgoaiTru extends HoSoBenhAn {
    private String toanThan;
    private String cacBoPhan;
    private String tomTatKetQuaCanlamSang;
    private String chuanDoanBanDau;
    private String daXuLy;
    private String chuanDoanKhiRaVien;
    private String dieuTriNgoaiTru;

}
