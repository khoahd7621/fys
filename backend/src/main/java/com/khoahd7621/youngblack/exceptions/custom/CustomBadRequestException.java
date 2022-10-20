package com.khoahd7621.youngblack.exceptions.custom;

import com.khoahd7621.youngblack.models.BaseResponse;

public class CustomBadRequestException extends BaseCustomException {

    public CustomBadRequestException(BaseResponse baseResponse) {
        super(baseResponse);
    }
    
}
