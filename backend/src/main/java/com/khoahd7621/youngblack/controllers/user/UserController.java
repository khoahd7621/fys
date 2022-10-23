package com.khoahd7621.youngblack.controllers.user;

import java.util.Map;

import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.models.user.dto.UserDTOResponse;
import org.springframework.web.bind.annotation.*;

import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;
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
}
