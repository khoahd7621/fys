package com.khoahd7621.youngblack.services;

import java.util.Map;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;

public interface UserService {

    public Map<String, UserDTOResponse> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException;

}
