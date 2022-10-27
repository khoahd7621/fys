package com.khoahd7621.youngblack.exceptions.custom;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;

public class CustomNotFoundException extends BaseCustomException {

    public CustomNotFoundException(ExceptionResponse exceptionResponse) {
        super(exceptionResponse);
    }

}
