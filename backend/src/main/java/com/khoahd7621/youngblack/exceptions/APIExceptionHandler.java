package com.khoahd7621.youngblack.exceptions;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBadRequestException(BadRequestException ex) {
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

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ExceptionResponse handleHttpRequestMethodNotSupportException(HttpRequestMethodNotSupportedException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ExceptionResponse handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
    public ExceptionResponse handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(MissingPathVariableException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleMissingPathVariableException(MissingPathVariableException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleServletRequestBindingException(ServletRequestBindingException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleConversionNotSupportedException(ConversionNotSupportedException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleTypeMismatchException(TypeMismatchException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handleHttpMessageNotWritableException(HttpMessageNotWritableException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMissingServletRequestPartException(MissingServletRequestPartException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBindException(BindException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNoHandlerFoundException(NoHandlerFoundException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex) {
        return ExceptionResponse.builder().code(-1).message(ex.getMessage()).build();
    }
}
