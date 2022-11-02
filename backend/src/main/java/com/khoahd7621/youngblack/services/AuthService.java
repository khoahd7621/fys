package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.request.user.UserRegisterRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.UserLoginResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserLoginRequest;

public interface AuthService {
    public SuccessResponse<UserLoginResponse> loginHandler(UserLoginRequest userLoginRequest) throws CustomBadRequestException;

    public User getUserLoggedIn() throws CustomNotFoundException;

    public SuccessResponse<NoData> userRegister(UserRegisterRequest userRegisterRequest) throws CustomBadRequestException;
}
