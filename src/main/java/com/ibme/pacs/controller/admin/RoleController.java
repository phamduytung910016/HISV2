package com.ibme.pacs.controller.admin;


import com.ibme.pacs.entities.ResponseObject;
import com.ibme.pacs.repository.IRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/admin/jobposition")
@RequiredArgsConstructor
@ResponseStatus
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
    private final IRoleRepository roleRepository;

    @GetMapping("/list")
    public ResponseEntity<ResponseObject> getAll() {
        return roleRepository.findAll().size() > 0 ? ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder().status("ok").message("Find all ").data(roleRepository.findAll()).build()) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        ResponseObject.builder().status("failed").message("Cannot find ").data(roleRepository.findAll()).build())
                ;
    }
}
