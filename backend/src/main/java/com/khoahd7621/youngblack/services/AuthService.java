package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.user.dto.UserDTOLoginRequest;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;

import java.util.Map;

public interface AuthService {
    public Map<String, UserDTOResponse> loginHandler(UserDTOLoginRequest userDTOLoginRequest) throws CustomBadRequestException;

}
