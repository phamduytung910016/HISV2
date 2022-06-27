package com.ibme.pacs.repository;

import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.TrangThai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IHoSoBenhAnNgoaiTruRepository extends JpaRepository<HoSoBenhAnNgoaiTru, Integer> {
    List<HoSoBenhAnNgoaiTru> findHoSoBenhAnNgoaiTruBySoLuuTruContains(String soLuuTru);
    List<HoSoBenhAnNgoaiTru> findAllBy();
    List<HoSoBenhAnNgoaiTru> findHoSoBenhAnNgoaiTruByTrangThaiContains(String trangThai);

}
