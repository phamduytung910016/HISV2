package com.ibme.pacs.controller.admin;


import com.ibme.pacs.dto.EmployeeDTO;
import com.ibme.pacs.dto.HoSoBenhAnNgoaiTruDTO;
import com.ibme.pacs.entities.Employee;
import com.ibme.pacs.entities.ResponseObject;
import com.ibme.pacs.service.inter.IHoSoBenhAnNgoaiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/admin/HSBANgoaiTru")
@RequiredArgsConstructor
@ResponseStatus
@CrossOrigin(origins = "http://localhost:4200")
public class HSBANgoaiTruController {
    private final IHoSoBenhAnNgoaiTruService hoSoBenhAnNgoaiTruService;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAll() {
        return hoSoBenhAnNgoaiTruService.findAll().size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder().status("ok").message("Find all Ho So Benh An Ngoai Tru").data(hoSoBenhAnNgoaiTruService.findAll()).build()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder().status("failed").message("Cannot find ").data(hoSoBenhAnNgoaiTruService.findAll()).build())
                ;
    }

    @ResponseStatus
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid HoSoBenhAnNgoaiTruDTO hoSoBenhAnNgoaiTruDTO, BindingResult bindingResult) throws Exception{
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
                        .message("Insert new Ho So Benh An ngoai Tru successfully")
                        .data(hoSoBenhAnNgoaiTruService.saveOrUpdate(hoSoBenhAnNgoaiTruDTO))
                        .build());

    }

    @GetMapping("/searchByTrangThai/{status}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findByTrangThai(@PathVariable(name = "status", required = true) String trangThai) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all Ho Sơ Benh An Ngoai Tru by status")
                        .data(hoSoBenhAnNgoaiTruService.findByStatus(trangThai))
                        .build()
        );
    }

    @GetMapping("/searchBySoLuuTru/{soLuTru}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findBySoLuTru(@PathVariable(name = "soLuuTru", required = true) String soLuuTru) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all HSBANgoaiTru by so luu tru")
                        .data(hoSoBenhAnNgoaiTruService.findBySoLuuTru(soLuuTru))
                        .build()
        );
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findById(@PathVariable(name = "id") int id) {
        return hoSoBenhAnNgoaiTruService.findById(id).isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find Ho So Benh An Ngoai Tru by id" + id)
                        .data(hoSoBenhAnNgoaiTruService.findById(id).get())
                        .build()
        ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder()
                                .status("Not found")
                                .message("Cannot found Ho So Benh An Ngoai Tru with id = " + id)
                                .build()
                );
    }

    @PostMapping("/update/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> update(@PathVariable(name = "id") int id, @RequestBody @Valid HoSoBenhAnNgoaiTruDTO hoSoBenhAnNgoaiTruDTO, BindingResult bindingResult) throws Exception{
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
        hoSoBenhAnNgoaiTruDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("The record is updated")
                        .data(hoSoBenhAnNgoaiTruService.saveOrUpdate(hoSoBenhAnNgoaiTruDTO))
                        .build());
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> delete(@PathVariable(name = "id") int id) {
        return hoSoBenhAnNgoaiTruService.delete(id) ?
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
