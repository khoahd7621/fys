package com.khoahd7621.youngblack.exceptions.custom;

import java.util.HashMap;
import java.util.Map;

import com.khoahd7621.youngblack.models.error.CustomError;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCustomException extends Exception {
    private Map<String, CustomError> error;

    public BaseCustomException(CustomError customError) {
        this.error = new HashMap<>();
        this.error.put("error", customError);
    }
}
