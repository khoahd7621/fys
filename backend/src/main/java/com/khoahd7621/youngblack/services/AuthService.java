package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOLoginRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOResponse;

public interface AuthService {
    public SuccessResponse<UserDTOResponse> loginHandler(UserDTOLoginRequest userDTOLoginRequest) throws CustomBadRequestException;

    public User getUserLoggedIn() throws CustomNotFoundException;
}
