package com.khoahd7621.youngblack.exceptions;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(CustomBadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse badRequestException(CustomBadRequestException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ExceptionResponse errors = ExceptionResponse.builder().build();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            errors.setCode(-1);
            errors.setMessage(error.getDefaultMessage());
        });
        return errors;
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse notFoundException(CustomNotFoundException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }
}
