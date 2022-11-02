package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.request.user.UserRegisterRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserUpdateRequest;

public interface UserService {

    public SuccessResponse<NoData> userRegister(UserRegisterRequest userRegisterRequest) throws BadRequestException;

    public SuccessResponse<UserResponse> getCurrentUser() throws NotFoundException;

    public SuccessResponse<UserResponse> updateUser(UserUpdateRequest userUpdateRequest) throws BadRequestException, NotFoundException;

    public SuccessResponse<NoData> changePassword(UserChangePasswordRequest userChangePasswordRequest) throws BadRequestException, NotFoundException;

}
