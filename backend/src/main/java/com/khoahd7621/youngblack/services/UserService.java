package com.khoahd7621.youngblack.services;

import java.util.Map;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;

public interface UserService {

    public Map<String, Object> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException;
    
}
