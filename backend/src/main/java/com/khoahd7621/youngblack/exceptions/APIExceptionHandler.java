package com.khoahd7621.youngblack.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.error.CustomError;
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
    public Map<String, CustomError> badRequestException(
            CustomBadRequestException ex) {
        return ex.getError();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, CustomError> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, CustomError> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errors.put("error", CustomError.builder().code(HttpStatus.BAD_REQUEST).message(errorMessage).build());
        });
        return errors;
    }

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public Map<String, CustomError> notFoundException(
            CustomBadRequestException ex) {
        return ex.getError();
    }
}
