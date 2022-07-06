package com.ibme.pacs.controller.admin;


import com.ibme.pacs.dto.HoSoBenhAnNgoaiTruDTO;
import com.ibme.pacs.dto.HoSoBenhAnNoiTruDTO;
import com.ibme.pacs.entities.ResponseObject;
import com.ibme.pacs.service.inter.IHoSoBenhAnNgoaiTruService;
import com.ibme.pacs.service.inter.IHoSoBenhAnNoiTruService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/admin/HSBANoiTru")
@RequiredArgsConstructor
@ResponseStatus
@CrossOrigin(origins = "http://localhost:4200")
public class HSBANoiTruController {
    private final IHoSoBenhAnNoiTruService hoSoBenhAnNoiTruService;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAll() {
        return hoSoBenhAnNoiTruService.findAll().size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder().status("ok").message("Find all Ho So Benh An Noi Tru").data(hoSoBenhAnNoiTruService.findAll()).build()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder().status("failed").message("Cannot find ").data(hoSoBenhAnNoiTruService.findAll()).build())
                ;
    }

    @ResponseStatus
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insert(@RequestBody @Valid HoSoBenhAnNoiTruDTO hoSoBenhAnNoiTruDTO, BindingResult bindingResult) throws Exception{
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
                        .data(hoSoBenhAnNoiTruService.saveOrUpdate(hoSoBenhAnNoiTruDTO))
                        .build());

    }

    @GetMapping("/searchByTrangThai/{status}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findByTrangThai(@PathVariable(name = "status", required = true) String trangThai) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find all Ho So Benh An Noi Tru by status")
                        .data(hoSoBenhAnNoiTruService.findByStatus(trangThai))
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
                        .data(hoSoBenhAnNoiTruService.findBySoLuuTru(soLuuTru))
                        .build()
        );
    }

    @GetMapping("/findById/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> findById(@PathVariable(name = "id") int id) {
        return hoSoBenhAnNoiTruService.findById(id).isPresent() ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("found")
                        .message("Find Ho So Benh An Noi Tru by id" + id)
                        .data(hoSoBenhAnNoiTruService.findById(id).get())
                        .build()
        ) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder()
                                .status("Not found")
                                .message("Cannot found Ho So Benh An Noi Tru with id = " + id)
                                .build()
                );
    }

    @PostMapping("/update/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> update(@PathVariable(name = "id") int id, @RequestBody @Valid HoSoBenhAnNoiTruDTO hoSoBenhAnNoiTruDTO, BindingResult bindingResult) throws Exception{
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
        hoSoBenhAnNoiTruDTO.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status("ok")
                        .message("The record is updated")
                        .data(hoSoBenhAnNoiTruService.saveOrUpdate(hoSoBenhAnNoiTruDTO))
                        .build());
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus
    public ResponseEntity<ResponseObject> delete(@PathVariable(name = "id") int id) {
        return hoSoBenhAnNoiTruService.delete(id) ?
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
