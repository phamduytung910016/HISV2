package com.ibme.pacs.dto;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SoTheoDoiSaiSotHSBADTO {
    private Integer id;
    private Integer hoSoBenhAnNgoaiTruId;
    private Integer hoSoBenhAnNoiTruId;
    private Integer employeeId;

    @NotEmpty(message = "Chưa nhập")
    private String ghiChu;
}
