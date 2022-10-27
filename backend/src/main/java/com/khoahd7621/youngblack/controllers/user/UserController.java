package com.khoahd7621.youngblack.controllers.user;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserDTOUpdateRequest;
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
    public SuccessResponse<UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        return userService.getCurrentUser();
    }

    @PutMapping
    public SuccessResponse<UserDTOResponse> updateUser(@Valid @RequestBody UserDTOUpdateRequest userDTOUpdateRequest) throws CustomBadRequestException, CustomNotFoundException {
        return userService.updateUser(userDTOUpdateRequest);
    }

    @PutMapping("/change-password")
    public SuccessResponse<NoData> changePassword(@Valid @RequestBody UserDTOChangePasswordRequest userDTOChangePasswordRequest) throws CustomBadRequestException, CustomNotFoundException {
        return userService.changePassword(userDTOChangePasswordRequest);
    }

}
