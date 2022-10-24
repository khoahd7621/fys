package com.khoahd7621.youngblack.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseExceptionResponse {
    private int code;
    private String message;
}
