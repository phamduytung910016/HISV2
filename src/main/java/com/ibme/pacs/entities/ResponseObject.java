package com.ibme.pacs.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

}
