package com.ibme.pacs.service.impl;

import com.ibme.pacs.dto.DepartmentDTO;
import com.ibme.pacs.entities.Department;
import com.ibme.pacs.repository.IDepartmentRepository;
import com.ibme.pacs.service.inter.IDepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class DeparmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Department> findById(int id) {
        return departmentRepository.findById(id);
    }

    @Override
    @Transactional
    public Department saveOrUpdate(DepartmentDTO departmentDTO) {
        Department entity = new Department();
        BeanUtils.copyProperties(departmentDTO,entity);
        return departmentRepository.save(entity);

    }

    @Override
    @Transactional
    public boolean delete(int id){
        boolean check = departmentRepository.findById(id).isPresent();
        if(check){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<Department> findByName(String name){
        return departmentRepository.findByNameContains(name);
    }

}
