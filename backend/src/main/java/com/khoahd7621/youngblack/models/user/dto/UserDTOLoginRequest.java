package com.khoahd7621.youngblack.models.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDTOLoginRequest {
    @NotBlank(message = "Email is required")
    @Email(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}", message = "Invalid email")
    private String email;
    
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 24, message = "The password must contain at least 6 characters and be up to 24 characters")
    private String password;
}
