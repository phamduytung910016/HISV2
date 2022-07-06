package com.ibme.pacs.controller.admin;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibme.pacs.dto.EmployeeDTO;
import com.ibme.pacs.dto.SoTheoDoiSaiSotHSBADTO;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.ResponseObject;
import com.ibme.pacs.entities.Role;
import com.ibme.pacs.repository.IEmployeeRepository;
import com.ibme.pacs.service.inter.IEmployeeService;
import com.ibme.pacs.service.inter.IHoSoBenhAnNgoaiTruService;
import com.ibme.pacs.service.inter.IHoSoBenhAnNoiTruService;
import com.ibme.pacs.service.inter.ISoTheoDoiSaiSotHSBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path = "/api/admin/soTheoDoi")
@RequiredArgsConstructor
@ResponseStatus
@CrossOrigin(origins = "http://localhost:4200")
public class SoTheoDoiHSBAController {
    private final ISoTheoDoiSaiSotHSBAService soTheoDoiSaiSotHSBAService;
    private final IEmployeeService employeeService;
    private final IHoSoBenhAnNoiTruService hoSoBenhAnNoiTruService;
    private final IHoSoBenhAnNgoaiTruService hoSoBenhAnNgoaiTruService;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAll() {
        return soTheoDoiSaiSotHSBAService.findAll().size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder().status("ok").message("Find all ").data(soTheoDoiSaiSotHSBAService.findAll()).build()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder().status("failed").message("Cannot find").data(soTheoDoiSaiSotHSBAService.findAll()).build())
                ;
    }

    @ResponseStatus
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid SoTheoDoiSaiSotHSBADTO soTheoDoiSaiSotHSBADTO, BindingResult bindingResult) {
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


        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("Insert successfully")
                        .data(soTheoDoiSaiSotHSBAService.saveOrUpdate(soTheoDoiSaiSotHSBADTO))
                        .build());

    }

    @GetMapping("/search/{employeeId}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findByEmployee(@PathVariable(name = "employeeId", required = true) int employeeId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Result")
                        .data(soTheoDoiSaiSotHSBAService.findSoTheoDoiSaiSotHSBAByEmployee(employeeService.findById(employeeId).get()))
                        .build()
        );
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findById(@PathVariable(name = "id") int id) {
        return soTheoDoiSaiSotHSBAService.findById(id).isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find by id" + id)
                        .data(employeeService.findById(id).get())
                        .build()
        ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder()
                                .status("Not found")
                                .message("Cannot found with id = " + id)
                                .build()
                );
    }

    @PostMapping("/update/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> update(@PathVariable(name = "id") int id, @RequestBody @Valid SoTheoDoiSaiSotHSBADTO soTheoDoiSaiSotHSBADTO, BindingResult bindingResult) {
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
        soTheoDoiSaiSotHSBADTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("The record is updated")
                        .data(soTheoDoiSaiSotHSBAService.saveOrUpdate(soTheoDoiSaiSotHSBADTO))
                        .build());
    }

    @GetMapping("/findByHSBANoiTru/{hsbaNoiTruId}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findByHoSoBenhAnNoiTru(@PathVariable(name = "hsbaNoiTruId", required = true) int hsbaNoiTruId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all employee by department")
                        .data(soTheoDoiSaiSotHSBAService.findSoTheoDoiSaiSotHSBAByHoSoBenhAnNoiTru(
                                hoSoBenhAnNoiTruService.findById(hsbaNoiTruId).get()
                        ))
                        .build()
        );
    }

    @GetMapping("/findByHSBANgoaiiTru/{hsbaNgoaiTruId}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findByHoSoBenhAnNgoaiTru(@PathVariable(name = "hsbaNgoaiTruId", required = true) int hsbaNgoaiTruId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all employee by department")
                        .data(soTheoDoiSaiSotHSBAService.findSoTheoDoiSaiSotHSBAByHoSoBenhAnNgoaiTru(
                                hoSoBenhAnNgoaiTruService.findById(hsbaNgoaiTruId).get()
                        ))
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> delete(@PathVariable(name = "id") int id) {
        return soTheoDoiSaiSotHSBAService.deleteById(id) ?
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



}
