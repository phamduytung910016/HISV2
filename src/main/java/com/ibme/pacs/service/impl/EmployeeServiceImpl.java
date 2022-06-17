package com.ibme.pacs.service.impl;

import com.ibme.pacs.dto.DepartmentDTO;
import com.ibme.pacs.dto.EmployeeDTO;
import com.ibme.pacs.entities.Department;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.repository.IDepartmentRepository;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.repository.IJobPositionRepository;
import com.ibme.pacs.repository.IRoleRepository;
import com.ibme.pacs.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService, UserDetailsService {


    private final PasswordEncoder passwordEncoder;

    private final IEmployeeRepository employeeRepository;
    private final IDepartmentRepository departmentRepository;
    private final IRoleRepository roleRepository;
    private final IJobPositionRepository jobPositionRepository;

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findById(int id){
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findByDepartment(int id){
        return employeeRepository.findEmployeesByDepartment_Id(id);
    }

    @Override
    public List<Employee> findByName(String name){
        return employeeRepository.findEmployeesByNameContains(name);
    }

    @Override
    public boolean delete(int id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Employee findByUserName(String username) {
        return  employeeRepository.findEmployeeByUsername(username);
    }

    @Override
    public Employee saveOrUpdate(EmployeeDTO employeeDTO) {
        Employee entity = new Employee();
        employeeDTO.setPassword(passwordEncoder.encode(employeeDTO.getPassword()));
        BeanUtils.copyProperties(employeeDTO,entity);
        entity.setDepartment(departmentRepository.findById(employeeDTO.getDepartment_id()).get());
        entity.setJobPosition(jobPositionRepository.findById(employeeDTO.getPosition_id()).get());
        entity.setRole(roleRepository.findById(employeeDTO.getRole_id()).get());
        return employeeRepository.save(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeeByUsername(username);
        if(employee == null){
            log.error("Employee not found in the database");
            throw new UsernameNotFoundException("Employee not found in the database");
        }else {
            log.info("Employee found in the database: {}", username);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(employee.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(employee.getUsername(),employee.getPassword(), authorities);
    }


}
