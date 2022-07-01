package com.ibme.pacs.service.impl;

import com.ibme.pacs.dto.SoTheoDoiSaiSotHSBADTO;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import com.ibme.pacs.entities.SoTheoDoiSaiSotHSBA;
import com.ibme.pacs.repository.*;
import com.ibme.pacs.service.inter.ISoTheoDoiSaiSotHSBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SoTheoDoiSaiSotHSBAServiceImpl implements ISoTheoDoiSaiSotHSBAService {
    private final IHoSoBenhAnNgoaiTruRepository hoSoBenhAnNgoaiTruRepository;
    private final IHoSoBenhAnNoiTruRepository hoSoBenhAnNoiTruRepository;
    private final IEmployeeRepository employeeRepository;
    private final ISoTheoDoiSaiSotHSBARepository soTheoDoiSaiSotHSBARepository;




    @Override
    public List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByEmployee(Employee employee) {
        return soTheoDoiSaiSotHSBARepository.findSoTheoDoiSaiSotHSBAByEmployee(employee);
    }

    @Override
    public List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByHoSoBenhAnNgoaiTru(HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru) {
        return soTheoDoiSaiSotHSBARepository.findSoTheoDoiSaiSotHSBAByHoSoBenhAnNgoaiTru(hoSoBenhAnNgoaiTru);
    }

    @Override
    public List<SoTheoDoiSaiSotHSBA> findSoTheoDoiSaiSotHSBAByHoSoBenhAnNoiTru(HoSoBenhAnNoiTru hoSoBenhAnNoiTru) {
        return soTheoDoiSaiSotHSBARepository.findSoTheoDoiSaiSotHSBAByHoSoBenhAnNoiTru(hoSoBenhAnNoiTru);
    }

    @Override
    public Optional<SoTheoDoiSaiSotHSBA> findById(int id) {
        return soTheoDoiSaiSotHSBARepository.findById(id);
    }

    @Override
    public List<SoTheoDoiSaiSotHSBA> findAll() {
        return soTheoDoiSaiSotHSBARepository.findAll();
    }

    @Override
    public SoTheoDoiSaiSotHSBA saveOrUpdate(SoTheoDoiSaiSotHSBADTO soTheoDoiSaiSotHSBADTO) {
        SoTheoDoiSaiSotHSBA soTheoDoiSaiSotHSBA = new SoTheoDoiSaiSotHSBA();
        BeanUtils.copyProperties(soTheoDoiSaiSotHSBADTO, soTheoDoiSaiSotHSBA);
        soTheoDoiSaiSotHSBA.setEmployee(employeeRepository.findById(soTheoDoiSaiSotHSBADTO.getEmployeeId()).get());
        if(soTheoDoiSaiSotHSBADTO.getHoSoBenhAnNoiTruId() == 0){
            soTheoDoiSaiSotHSBA.setHoSoBenhAnNoiTru(null);
            soTheoDoiSaiSotHSBA.setHoSoBenhAnNgoaiTru(hoSoBenhAnNgoaiTruRepository.findById(soTheoDoiSaiSotHSBADTO.getHoSoBenhAnNgoaiTruId()).get());
        }else if(soTheoDoiSaiSotHSBADTO.getHoSoBenhAnNgoaiTruId() == 0){
            soTheoDoiSaiSotHSBA.setHoSoBenhAnNgoaiTru(null);
            soTheoDoiSaiSotHSBA.setHoSoBenhAnNoiTru(hoSoBenhAnNoiTruRepository.findById(soTheoDoiSaiSotHSBADTO.getHoSoBenhAnNoiTruId()).get());
        }
        return soTheoDoiSaiSotHSBARepository.save(soTheoDoiSaiSotHSBA);
    }

    @Override
    public boolean deleteById(int id) {
        if(soTheoDoiSaiSotHSBARepository.existsById(id)){
            soTheoDoiSaiSotHSBARepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
