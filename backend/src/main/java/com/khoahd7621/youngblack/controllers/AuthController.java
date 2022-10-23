package com.khoahd7621.youngblack.controllers;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.user.dto.UserDTOLoginRequest;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;
import com.khoahd7621.youngblack.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public Map<String, UserDTOResponse> login(@Valid @RequestBody UserDTOLoginRequest userDTOLoginRequest) throws CustomBadRequestException {
        return authService.loginHandler(userDTOLoginRequest);
    }
}
