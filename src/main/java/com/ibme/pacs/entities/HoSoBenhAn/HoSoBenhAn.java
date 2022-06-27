package com.ibme.pacs.entities.HoSoBenhAn;

import com.ibme.pacs.entities.Department;
import com.ibme.pacs.entities.Employee;
import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class HoSoBenhAn extends ThongTinHanhChinh{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String soYTe;
    private String benhVien;

    @ManyToOne
    @JoinColumn(name = "khoa_phong_id")
    private Department khoaPhong;

    private String soLuuTru;

    private String bacSiLamBenhAn;

    @ManyToOne
    @JoinColumn(name = "nhan_vien_luu_tru_id")
    private Employee nhanVienLuTru;

    private String trangThai;

}
