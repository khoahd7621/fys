package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.request.user.UserRegisterRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserUpdateRequest;

public interface UserService {

    public SuccessResponse<NoData> userRegister(UserRegisterRequest userRegisterRequest) throws CustomBadRequestException;

    public SuccessResponse<UserResponse> getCurrentUser() throws CustomNotFoundException;

    public SuccessResponse<UserResponse> updateUser(UserUpdateRequest userUpdateRequest) throws CustomBadRequestException, CustomNotFoundException;

    public SuccessResponse<NoData> changePassword(UserChangePasswordRequest userChangePasswordRequest) throws CustomBadRequestException, CustomNotFoundException;

    public SuccessResponse<ListUsersWithPaginateResponse> getListUsers(int limit, int offset);
}
