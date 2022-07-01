package com.ibme.pacs.service.impl;

import com.ibme.pacs.dto.HoSoBenhAnNoiTruDTO;
import com.ibme.pacs.entities.HoSoBenhAn.NoiTru.HoSoBenhAnNoiTru;
import com.ibme.pacs.repository.IDepartmentRepository;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.repository.IHoSoBenhAnNgoaiTruRepository;
import com.ibme.pacs.repository.IHoSoBenhAnNoiTruRepository;
import com.ibme.pacs.service.inter.IHoSoBenhAnNoiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class HoSoBenhAnNoiTruServiceImpl implements IHoSoBenhAnNoiTruService {

    private final IHoSoBenhAnNoiTruRepository hoSoBenhAnNoiTruRepository;
    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;

    @Override
    public List<HoSoBenhAnNoiTru> findAll() {
        return hoSoBenhAnNoiTruRepository.findAll();
    }

    @Override
    public List<HoSoBenhAnNoiTru> findBySoLuuTru(String soLuuTru) {
        return hoSoBenhAnNoiTruRepository.findHoSoBenhAnNoiTruBySoLuuTruContains(soLuuTru);
    }

    @Override
    public Optional<HoSoBenhAnNoiTru> findById(int id) {
        return hoSoBenhAnNoiTruRepository.findById(id);
    }

    @Override
    public HoSoBenhAnNoiTru saveOrUpdate(HoSoBenhAnNoiTruDTO hoSoBenhAnNoiTruTruDTO) throws Exception {
        HoSoBenhAnNoiTru hoSoBenhAnNoiTru = new HoSoBenhAnNoiTru();
        BeanUtils.copyProperties(hoSoBenhAnNoiTruTruDTO, hoSoBenhAnNoiTru);
        hoSoBenhAnNoiTru.setNhanVienLuTru(employeeRepository.findById(hoSoBenhAnNoiTruTruDTO.getNhanVienLuTruId().intValue()).get());
        hoSoBenhAnNoiTru.setKhoaPhong(departmentRepository.findById(hoSoBenhAnNoiTruTruDTO.getKhoaPhongId().intValue()).get());
        return hoSoBenhAnNoiTruRepository.save(hoSoBenhAnNoiTru);
    }

    @Override
    public List<HoSoBenhAnNoiTru> findByStatus(String trangThai) {
        return hoSoBenhAnNoiTruRepository.findHoSoBenhAnNoiTruByTrangThaiContains(trangThai);
    }

    @Override
    public boolean delete(int id) {
        if(hoSoBenhAnNoiTruRepository.existsById(id)){
            hoSoBenhAnNoiTruRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
