package com.ibme.pacs.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class EmployeeDTO {
    private int id;
    @NotEmpty(message = "Chưa nhập tên")
    private String name;

    @NotEmpty(message = "Chưa nhập username")
    private String username;

    @NotEmpty(message = "Chưa nhập email")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotEmpty(message = "Chưa nhập mật khẩu")
    private String password;


    private Integer department_id;


    private Integer position_id;


    private Integer role_id;
}
