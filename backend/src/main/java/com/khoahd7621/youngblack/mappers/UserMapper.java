package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.dtos.response.user.UserDTOResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.khoahd7621.youngblack.constants.ERoles;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.dtos.request.user.UserDTORegisterRequest;

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
                .role(ERoles.USER)
                .status(EAccountStatus.ACTIVE)
                .build();
    }

    public UserDTOResponse toUserDTOResponse(User user) {
        return UserDTOResponse.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phone(user.getPhone())
                .address(user.getAddress())
                .role(user.getRole())
                .build();
    }

}
