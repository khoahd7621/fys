package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    public <T> SuccessResponse<T> toBaseSuccessResponse(T data, String message) {
        return new SuccessResponse<>(data, message);
    }

    public ExceptionResponse toBaseExceptionResponse(String message) {
        return ExceptionResponse.builder().message(message).build();
    }
}
