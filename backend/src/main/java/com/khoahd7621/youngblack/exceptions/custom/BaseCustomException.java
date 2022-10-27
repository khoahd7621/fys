package com.khoahd7621.youngblack.exceptions.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseCustomException extends Exception {
    public BaseCustomException(String message) {
        super(message);
    }
}
