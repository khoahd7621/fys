package com.khoahd7621.youngblack.dtos.response.user;

import com.khoahd7621.youngblack.constants.EAccountStatus;
import com.khoahd7621.youngblack.constants.ERoles;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private ERoles role;
    private EAccountStatus status;
    private Date createdAt;
    private Date updatedAt;
    private String accessToken;
    private String refreshToken;
}
