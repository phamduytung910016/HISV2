package com.ibme.pacs.service.impl;

import com.ibme.pacs.dto.HoSoBenhAnNgoaiTruDTO;
import com.ibme.pacs.entities.HoSoBenhAn.NgoaiTru.HoSoBenhAnNgoaiTru;
import com.ibme.pacs.entities.HoSoBenhAn.TrangThai;
import com.ibme.pacs.repository.IDepartmentRepository;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.repository.IHoSoBenhAnNgoaiTruRepository;
import com.ibme.pacs.service.inter.IHoSoBenhAnNgoaiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class HoSoBenhAnNgoaiTruServiceImpl implements IHoSoBenhAnNgoaiTruService {
    private final IHoSoBenhAnNgoaiTruRepository hoSoBenhAnNgoaiTruRepository;
    private final IEmployeeRepository employeeRepository;
    private IDepartmentRepository departmentRepository;

    @Override
    public List<HoSoBenhAnNgoaiTru> findAll() {
        return hoSoBenhAnNgoaiTruRepository.findAll();
    }

    @Override
    public List<HoSoBenhAnNgoaiTru> findBySoLuuTru(String soLuuTru) {
       return hoSoBenhAnNgoaiTruRepository.findHoSoBenhAnNgoaiTruBySoLuuTruContains(soLuuTru);
    }

    @Override
    public HoSoBenhAnNgoaiTru findById(int id) {
        return hoSoBenhAnNgoaiTruRepository.findById(id).get();
    }

    @Override
    public HoSoBenhAnNgoaiTru saveOrUpdate(HoSoBenhAnNgoaiTruDTO hoSoBenhAnNgoaiTruDTO) {
        HoSoBenhAnNgoaiTru hoSoBenhAnNgoaiTru = new HoSoBenhAnNgoaiTru();
        BeanUtils.copyProperties(hoSoBenhAnNgoaiTruDTO,hoSoBenhAnNgoaiTru);
        hoSoBenhAnNgoaiTru.setKhoaPhong(departmentRepository.findById(hoSoBenhAnNgoaiTruDTO.getKhoaPhongId()).get());
        hoSoBenhAnNgoaiTru.setNhanVienLuTru(employeeRepository.findById(hoSoBenhAnNgoaiTruDTO.getNhanVienLuTruId()).get());

        return hoSoBenhAnNgoaiTruRepository.save(hoSoBenhAnNgoaiTru);
    }

    @Override
    public List<HoSoBenhAnNgoaiTru> findByStatus(String trangThai) {
        return hoSoBenhAnNgoaiTruRepository.findHoSoBenhAnNgoaiTruByTrangThaiContains(trangThai);
    }

    @Override
    public boolean delete(int id) {
        if(hoSoBenhAnNgoaiTruRepository.existsById(id)){
            hoSoBenhAnNgoaiTruRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
