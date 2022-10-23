package com.khoahd7621.youngblack.services;

import java.util.Map;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.models.user.dto.UserDTOChangePasswordRequest;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;
import com.khoahd7621.youngblack.models.user.dto.UserDTOUpdateRequest;

public interface UserService {

    public Map<String, UserDTOResponse> userRegister(UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException;

    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException;

    public Map<String, UserDTOResponse> updateUser(UserDTOUpdateRequest userDTOUpdateRequest) throws CustomBadRequestException, CustomNotFoundException;

    public Map<String, UserDTOResponse> changePassword(UserDTOChangePasswordRequest userDTOChangePasswordRequest) throws CustomBadRequestException, CustomNotFoundException;
}
