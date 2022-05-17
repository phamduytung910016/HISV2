package com.ibme.pacs.service.inter;

import com.ibme.pacs.dto.DepartmentDTO;
import com.ibme.pacs.entities.Department;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface IDepartmentService {
    List<Department> findAll();

    @Transactional
    Optional<Department> findById(int id);

    @Transactional
    Department saveOrUpdate(DepartmentDTO departmentDTO);

    @Transactional
    boolean delete(int id);

    @Transactional
    List<Department> findByName(String name);
}
