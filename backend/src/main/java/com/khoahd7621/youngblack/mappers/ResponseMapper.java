package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.response.BaseExceptionResponse;
import com.khoahd7621.youngblack.dtos.response.BaseSuccessResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {
    public <T> BaseSuccessResponse<T> toBaseSuccessResponse(T data, String message) {
        return new BaseSuccessResponse<>(message, data);
    }

    public BaseExceptionResponse toBaseExceptionResponse(String message) {
        return BaseExceptionResponse.builder().message(message).build();
    }
}
