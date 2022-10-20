package com.khoahd7621.youngblack.exceptions.custom;

import java.util.Map;

import com.khoahd7621.youngblack.models.BaseResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCustomException extends Exception {
    private Map<String, Object> errors;

    public BaseCustomException(BaseResponse baseResponse) {
        this.errors = baseResponse.getResponse();
    }
}
