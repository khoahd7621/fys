package com.khoahd7621.youngblack.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExceptionResponse {
    private final int code = -1;
    private String message;
}
