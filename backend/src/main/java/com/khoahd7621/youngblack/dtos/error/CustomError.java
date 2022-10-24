package com.khoahd7621.youngblack.dtos.error;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class CustomError {
    private HttpStatus code;
    private String message;
}
