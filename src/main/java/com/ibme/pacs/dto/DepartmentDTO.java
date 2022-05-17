package com.ibme.pacs.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class DepartmentDTO {
    private Integer id;
    @NotBlank(message = "Name is required")
    private String name;
}
