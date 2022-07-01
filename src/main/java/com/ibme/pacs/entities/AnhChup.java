package com.ibme.pacs.entities;

import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class AnhChup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String anh;

    @ManyToOne
    @JoinColumn(name = "hoSoBenhAnNgoaiTru_id", nullable = true)
    private HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru;

    @ManyToOne
    @JoinColumn(name = "hoSoBenhAnNoiTru_id", nullable = true)
    private HoSoBenhAnNoiTru hoSoBenhAnNoiTru;
}
