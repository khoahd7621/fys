package com.khoahd7621.youngblack.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;

@RestControllerAdvice
public class APIExceptionHandler {
    
    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public Map<String, Object> badRequestException(CustomBadRequestException ex) {
        return ex.getErrors();
    }
}
