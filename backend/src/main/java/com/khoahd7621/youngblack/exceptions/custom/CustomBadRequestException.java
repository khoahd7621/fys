package com.khoahd7621.youngblack.exceptions.custom;

import com.khoahd7621.youngblack.models.error.CustomError;

public class CustomBadRequestException extends BaseCustomException {

    public CustomBadRequestException(CustomError customError) {
        super(customError);
    }

}
