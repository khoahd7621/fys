package com.khoahd7621.youngblack.exceptions.custom;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;

public class CustomBadRequestException extends BaseCustomException {

    public CustomBadRequestException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse);
    }

}
