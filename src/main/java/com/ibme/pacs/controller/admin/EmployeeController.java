package com.ibme.pacs.controller.admin;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibme.pacs.dto.DepartmentDTO;
import com.ibme.pacs.dto.EmployeeDTO;
import com.ibme.pacs.entities.Department;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.ResponseObject;
import com.ibme.pacs.entities.Role;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.service.inter.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api/admin/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeService employeeService;
    private final IEmployeeRepository employeeRepository;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAll() {
        return employeeService.findAll().size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder().status("ok").message("Find all departments").data(employeeService.findAll()).build()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder().status("failed").message("Cannot find department").data(employeeService.findAll()).build())
                ;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error)->{
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status("failed")
                            .message("Bad request")
                            .data(errors)
                            .build()
            );
        }

        List<Employee> findEmail = employeeRepository.findEmployeeByEmail(employeeDTO.getEmail().trim());
        Employee findUserName = employeeRepository.findEmployeeByUsername(employeeDTO.getEmail().trim());
        if (findEmail.size() > 0 || findUserName != null) {
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status("failed")
                            .message("The email or username record has been already exist")
                            .data(findEmail)
                            .build());
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("Insert new department successfully")
                        .data(employeeService.saveOrUpdate(employeeDTO))
                        .build());

    }

    @GetMapping("/search/{name}")
    public ResponseEntity<ResponseObject> findByName(@PathVariable(name = "name", required = true) String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all department by name")
                        .data(employeeService.findByName(name))
                        .build()
        );
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable(name = "id") int id) {
        return employeeService.findById(id).isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find employee by id" + id)
                        .data(employeeService.findById(id).get())
                        .build()
        ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder()
                                .status("Not found")
                                .message("Cannot found employee with id = " + id)
                                .build()
                );
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<ResponseObject> update(@PathVariable(name = "id") int id, @RequestBody @Valid EmployeeDTO employeeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getAllErrors().forEach((error)->{
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return ResponseEntity.badRequest().body(
                    ResponseObject.builder()
                            .status("failed")
                            .message("Bad request")
                            .data(errors)
                            .build()
            );
        }
        employeeDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("The record is updated")
                        .data(employeeService.saveOrUpdate(employeeDTO))
                        .build());
    }

    @GetMapping("/findByDepartment/{departmentId}")
    public ResponseEntity<ResponseObject> findByDepartment(@PathVariable(name = "departmentId", required = true) int id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all department by name")
                        .data(employeeService.findByDepartment(id))
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable(name = "id") int id) {
        return employeeService.delete(id) ?
                ResponseEntity.status(HttpStatus.OK).body(
                        ResponseObject.builder()
                                .status("ok")
                                .message("The record is deleted")
                                .build())
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseObject.builder()
                        .status("not implement")
                        .message("Cannot found the record")
                        .build()
        );
    }

    @PostMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("IBME-")) {
            try {
                String refresh_token = authorizationHeader.substring("IBME-".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Employee employee = employeeRepository.findEmployeeByUsername(username);
                List<Role> roles = new ArrayList<>();
                roles.add(employee.getRole());
                String access_token = JWT.create().withSubject(employee.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("role", roles.stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String,String> tokens = new HashMap<>();
                tokens.put("access_token",access_token);
                tokens.put("refresh_token",refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(),tokens);

            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

}
