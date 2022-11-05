package com.khoahd7621.youngblack.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends Exception {
    public BaseException(String message) {
        super(message);
    }
}
