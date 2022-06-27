package com.ibme.pacs.service.inter;

import com.ibme.pacs.dto.HoSoBenhAnNgoaiTruDTO;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IHoSoBenhAnNgoaiTruService {
    List<HoSoBenhAnNgoaiTru> findAll();
    List<HoSoBenhAnNgoaiTru> findBySoLuuTru(String soLuuTru);
    HoSoBenhAnNgoaiTru findById(int id);
    HoSoBenhAnNgoaiTru saveOrUpdate(HoSoBenhAnNgoaiTruDTO hoSoBenhAnNgoaiTruDTO);
    List<HoSoBenhAnNgoaiTru> findByStatus(String trangThai);
    boolean delete(int id);
}
