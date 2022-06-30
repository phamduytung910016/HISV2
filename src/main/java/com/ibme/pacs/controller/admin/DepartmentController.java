package com.ibme.pacs.controller.admin;

import com.ibme.pacs.dto.DepartmentDTO;
import com.ibme.pacs.entities.Department;

import com.ibme.pacs.entities.ResponseObject;
import com.ibme.pacs.repository.IRoleRepository;
import com.ibme.pacs.service.impl.DeparmentServiceImpl;
import com.ibme.pacs.service.inter.IDepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/admin/department")
@RequiredArgsConstructor
@ResponseStatus
public class DepartmentController {



    private final IDepartmentService departmentService;

    @Autowired
    private IRoleRepository roleRepository;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAll() {
        return departmentService.findAll().size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder().status("ok").message("Find all departments").data(departmentService.findAll()).build()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder().status("failed").message("Cannot find department").data(departmentService.findAll()).build())
                ;
    }

    @PostMapping("/add")
    @ResponseStatus
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid DepartmentDTO departmentDTO, BindingResult bindingResult) {
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

        List<Department> findDepartByName = departmentService.findByName(departmentDTO.getName().trim());
        if (findDepartByName.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    ResponseObject.builder()
                            .status("failed")
                            .message("The new record has been already exist")
                            .data(findDepartByName)
                            .build());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("Insert new department successfully")
                        .data(departmentService.saveOrUpdate(departmentDTO))
                        .build());

    }

    @GetMapping("/search/{name}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findByName(@PathVariable(name = "name", required = true) String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all department by name")
                        .data(departmentService.findByName(name))
                        .build()
        );
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findById(@PathVariable(name = "id") int id) {
        return departmentService.findById(id).isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find department by id" + id)
                        .data(departmentService.findById(id).get())
                        .build()
        ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder()
                                .status("Not found")
                                .message("Cannot found department with id = " + id)
                                .build()
                );
    }

    @PostMapping("/update/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> update(@PathVariable(name = "id") int id, @RequestBody DepartmentDTO departmentDTO) {
        departmentDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("The record is updated")
                        .data(departmentDTO)
                        .build());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> delete(@PathVariable(name = "id") int id) {
        return departmentService.delete(id) ?
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
