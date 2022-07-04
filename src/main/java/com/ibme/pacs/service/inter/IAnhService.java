package com.ibme.pacs.service.inter;

import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;

import java.util.List;
import java.util.Optional;

public interface IAnhService {
    List<AnhChup> findAll();


    Optional<AnhChup> findById(int id);


    List<AnhChup> findByHSBANgoaiTru(HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru);


    List<AnhChup> findByHSBANoiTru(HoSoBenhAnNoiTru hoSoBenhAnNoiTru);

    boolean delete(int id);

    AnhChup saveOrUpdate(AnhChupDTO anhChupDTO);
}
