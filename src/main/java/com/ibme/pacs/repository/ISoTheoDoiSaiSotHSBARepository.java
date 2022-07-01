package com.ibme.pacs.repository;

import com.ibme.pacs.dto.SoTheoDoiSaiSotHSBADTO;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import com.ibme.pacs.entities.SoTheoDoiSaiSotHSBA;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISoTheoDoiSaiSotHSBARepository extends JpaRepository<SoTheoDoiSaiSotHSBA, Integer> {
    List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByEmployee(Employee employee);
    List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByHoSoBenhAnNgoaiTru(HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru);
    List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByHoSoBenhAnNoiTru(HoSoBenhAnNoiTru hoSoBenhAnNoiTru);
    Optional<SoTheoDoiSaiSotHSBA> findById(int id);


}
