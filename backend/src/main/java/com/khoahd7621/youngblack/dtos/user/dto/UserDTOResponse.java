package com.khoahd7621.youngblack.dtos.user.dto;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.constants.ERoles;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTOResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private ERoles role;
    private EAccountStatus status;
    private String accessToken;
    private String refreshToken;
}
