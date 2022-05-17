package com.ibme.pacs.repository;

import com.ibme.pacs.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findEmployeesByDepartment_Id(Integer departmentId);

    List<Employee> findEmployeesByJobPosition_Id(Integer positionId);


    List<Employee> findEmployeesByDepartment_IdAndJobPosition_Id(Integer departmentId, Integer positionId);

    List<Employee> findEmployeesByNameContains(String name);

    List<Employee> findEmployeeByEmail(String email);

    Employee findEmployeeByUsername(String name);

}
