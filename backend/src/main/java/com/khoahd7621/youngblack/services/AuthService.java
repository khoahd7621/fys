package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.request.user.UserDTORegisterRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOLoginResponse;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOLoginRequest;

public interface AuthService {
    public SuccessResponse<UserDTOLoginResponse> loginHandler(UserDTOLoginRequest userDTOLoginRequest) throws CustomBadRequestException;

    public User getUserLoggedIn() throws CustomNotFoundException;

    public SuccessResponse<NoData> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException;
}
