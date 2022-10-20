package com.khoahd7621.youngblack.models.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTORegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
}
