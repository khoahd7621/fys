package com.khoahd7621.youngblack.dtos.response.user;

import com.khoahd7621.youngblack.constants.ERoles;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserDTOResponse {
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private ERoles role;
    private Date createdAt;
    private Date updatedAt;
    private String accessToken;
    private String refreshToken;
}
