package com.khoahd7621.youngblack.controllers.user;

import java.util.Map;

import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTOChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTOResponse;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTOUpdateRequest;
import org.springframework.web.bind.annotation.*;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.dtos.user.dto.UserDTORegisterRequest;
import com.khoahd7621.youngblack.services.UserService;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Map<String, UserDTOResponse> userRegister(@Valid @RequestBody UserDTORegisterRequest userDTORegisterRequest) throws CustomBadRequestException {
        return userService.userRegister(userDTORegisterRequest);
    }

    @GetMapping
    public Map<String, UserDTOResponse> getCurrentUser() throws CustomNotFoundException {
        return userService.getCurrentUser();
    }

    @PutMapping
    public Map<String, UserDTOResponse> updateUser(@Valid @RequestBody UserDTOUpdateRequest userDTOUpdateRequest) throws CustomBadRequestException, CustomNotFoundException {
        return userService.updateUser(userDTOUpdateRequest);
    }

    @PutMapping("/change-password")
    public Map<String, UserDTOResponse> changePassword(@Valid @RequestBody UserDTOChangePasswordRequest userDTOChangePasswordRequest) throws CustomBadRequestException, CustomNotFoundException {
        return userService.changePassword(userDTOChangePasswordRequest);
    }
}
