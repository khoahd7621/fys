package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.request.user.UserDTORegisterRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOUpdateRequest;

public interface UserService {

    public SuccessResponse<NoData> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException;

    public SuccessResponse<UserDTOResponse> getCurrentUser() throws CustomNotFoundException;

    public SuccessResponse<UserDTOResponse> updateUser(UserDTOUpdateRequest userDTOUpdateRequest) throws CustomBadRequestException, CustomNotFoundException;

    public SuccessResponse<NoData> changePassword(UserDTOChangePasswordRequest userDTOChangePasswordRequest) throws CustomBadRequestException, CustomNotFoundException;

    public SuccessResponse<ListUsersWithPaginateResponse> getListUsers(int limit, int offset);
}
