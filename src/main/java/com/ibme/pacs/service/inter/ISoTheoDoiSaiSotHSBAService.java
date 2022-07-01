package com.ibme.pacs.service.inter;

import com.ibme.pacs.dto.SoTheoDoiSaiSotHSBADTO;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import com.ibme.pacs.entities.SoTheoDoiSaiSotHSBA;

import java.util.List;
import java.util.Optional;


public interface ISoTheoDoiSaiSotHSBAService {
    List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByEmployee(Employee employee);
    List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByHoSoBenhAnNgoaiTru(HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru);
    List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByHoSoBenhAnNoiTru(HoSoBenhAnNoiTru hoSoBenhAnNoiTru);
    Optional<SoTheoDoiSaiSotHSBA> findById(int id);
    List<SoTheoDoiSaiSotHSBA> findAll();
    SoTheoDoiSaiSotHSBA saveOrUpdate(SoTheoDoiSaiSotHSBADTO soTheoDoiSaiSotHSBADTO);
    boolean deleteById(int id);
}
