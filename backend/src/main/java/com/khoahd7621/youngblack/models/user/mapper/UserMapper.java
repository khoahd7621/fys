package com.khoahd7621.youngblack.models.user.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.khoahd7621.youngblack.constants.ERoles;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.models.user.dto.UserDTORegisterRequest;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toUser(UserDTORegisterRequest userDTORegisterRequest) {
        return User.builder()
            .email(userDTORegisterRequest.getEmail())
            .phone(userDTORegisterRequest.getPhone())
            .firstName(userDTORegisterRequest.getFirstName())
            .lastName(userDTORegisterRequest.getLastName())
            .password(passwordEncoder.encode(userDTORegisterRequest.getPassword()))
            .role(ERoles.USER).build();
    }

}
