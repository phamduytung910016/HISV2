package com.ibme.pacs;

import com.ibme.pacs.dto.EmployeeDTO;
import com.ibme.pacs.service.inter.IEmployeeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class PacsApplication {


    public static void main(String[] args) {
        SpringApplication.run(PacsApplication.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    CommandLineRunner run(IEmployeeService employeeService) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                employeeService.saveOrUpdate(EmployeeDTO.builder()
//                        .name("Phạm Duy Tùng")
//                        .username("tungpham14")
//                        .email("tungpham@gmail.com")
//                        .password("1234")
//                        .department_id(1)
//                        .position_id(1)
//                        .role_id(1)
//                        .build());
//                employeeService.saveOrUpdate(EmployeeDTO.builder()
//                        .name("Đinh Hà Vy")
//                        .username("havy14")
//                        .email("havy@gmail.com")
//                        .password("1234")
//                        .department_id(1)
//                        .position_id(1)
//                        .role_id(3)
//                        .build());
//
//            }
//        };
//    }

}
