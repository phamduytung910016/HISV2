package com.ibme.pacs.repository;

import com.ibme.pacs.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByNameContains(String name);

}
