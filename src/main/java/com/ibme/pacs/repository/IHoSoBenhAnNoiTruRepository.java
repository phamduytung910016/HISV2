package com.ibme.pacs.repository;

import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IHoSoBenhAnNoiTruRepository extends JpaRepository<HoSoBenhAnNoiTru, Integer> {
    List<HoSoBenhAnNoiTru> findHoSoBenhAnNoiTruBySoLuuTruContains(String soLuuTru);
    List<HoSoBenhAnNoiTru> findAllBy();
    List<HoSoBenhAnNoiTru> findHoSoBenhAnNoiTruByTrangThaiContains(String trangThai);
}
