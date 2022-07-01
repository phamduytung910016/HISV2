package com.ibme.pacs.entities;

import com.ibme.pacs.entities.HoSoBenhAn.HoSoBenhAn;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "so_theo_doi_sai_sot_hsba")
public class SoTheoDoiSaiSotHSBA {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "hoSoBenhAnNgoaiTru_id", nullable = true)
    private HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru;

    @ManyToOne
    @JoinColumn(name = "hoSoBenhAnNoiTru_id", nullable = true)
    private HoSoBenhAnNoiTru hoSoBenhAnNoiTru;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private String ghiChu;

}
