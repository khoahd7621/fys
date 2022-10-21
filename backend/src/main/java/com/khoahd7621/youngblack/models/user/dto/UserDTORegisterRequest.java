package com.khoahd7621.youngblack.models.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UserDTORegisterRequest {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Invalid email")
    private String email;
    @Pattern(regexp = "(84|0[3|5|7|8|9])+([0-9]{8})\\b", message = "Invalid phone number")
    private String phone;
    @Size(min = 6, max = 24, message = "The password must contain at least 6 characters and be up to 24 characters")
    private String password;
}
