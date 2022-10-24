package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOLoginRequest;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTOResponse;

import java.util.Map;

public interface AuthService {
    public Map<String, UserDTOResponse> loginHandler(UserDTOLoginRequest userDTOLoginRequest) throws CustomBadRequestException;

    public User getUserLoggedIn() throws CustomNotFoundException;
}
