package com.khoahd7621.youngblack.controllers;

import com.khoahd7621.youngblack.dtos.request.user.UserRegisterRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.user.UserLoginResponse;
import com.khoahd7621.youngblack.exceptions.BadRequestException;
import com.khoahd7621.youngblack.dtos.request.user.UserLoginRequest;
import com.khoahd7621.youngblack.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public SuccessResponse<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest) throws BadRequestException {
        return authService.loginHandler(userLoginRequest);
    }

    @PostMapping("/register")
    public SuccessResponse<NoData> userRegister(@Valid @RequestBody UserRegisterRequest userRegisterRequest) throws BadRequestException {
        return authService.userRegister(userRegisterRequest);
    }
}
