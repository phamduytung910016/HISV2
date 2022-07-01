package com.ibme.pacs.service.inter;

import com.ibme.pacs.dto.HoSoBenhAnNgoaiTruDTO;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface IHoSoBenhAnNgoaiTruService {
    List<HoSoBenhAnNgoaiTru> findAll();
    List<HoSoBenhAnNgoaiTru> findBySoLuuTru(String soLuuTru);
    Optional<HoSoBenhAnNgoaiTru> findById(int id);
    HoSoBenhAnNgoaiTru saveOrUpdate(HoSoBenhAnNgoaiTruDTO hoSoBenhAnNgoaiTruDTO) throws Exception;
    List<HoSoBenhAnNgoaiTru> findByStatus(String trangThai);
    boolean delete(int id);
}
