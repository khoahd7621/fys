package com.khoahd7621.youngblack.exceptions.custom;

import com.khoahd7621.youngblack.models.error.CustomError;

public class CustomNotFoundException extends BaseCustomException {

    public CustomNotFoundException(CustomError customError) {
        super(customError);
    }

}
