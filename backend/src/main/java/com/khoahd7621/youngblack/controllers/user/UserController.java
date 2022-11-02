package com.khoahd7621.youngblack.controllers.user;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/current")
    public SuccessResponse<UserResponse> getCurrentUser() throws CustomNotFoundException {
        return userService.getCurrentUser();
    }

    @PutMapping
    public SuccessResponse<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) throws CustomBadRequestException, CustomNotFoundException {
        return userService.updateUser(userUpdateRequest);
    }

    @PutMapping("/change-password")
    public SuccessResponse<NoData> changePassword(@Valid @RequestBody UserChangePasswordRequest userChangePasswordRequest) throws CustomBadRequestException, CustomNotFoundException {
        return userService.changePassword(userChangePasswordRequest);
    }

}
