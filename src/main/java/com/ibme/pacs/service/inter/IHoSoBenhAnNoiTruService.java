package com.ibme.pacs.service.inter;

import com.ibme.pacs.dto.HoSoBenhAnNoiTruDTO;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Transactional
public interface IHoSoBenhAnNoiTruService {
    List<HoSoBenhAnNoiTru> findAll();
    List<HoSoBenhAnNoiTru> findBySoLuuTru(String soLuuTru);
   Optional<HoSoBenhAnNoiTru> findById(int id);
    HoSoBenhAnNoiTru saveOrUpdate(HoSoBenhAnNoiTruDTO HoSoBenhAnNoiTruTruDTO) throws Exception;
    List<HoSoBenhAnNoiTru> findByStatus(String trangThai);
    boolean delete(int id);
}
