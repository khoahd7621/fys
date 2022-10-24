package com.khoahd7621.youngblack.dtos.request.user;

import com.khoahd7621.youngblack.constants.Constants;
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
    @Email(regexp = Constants.EMAIL_REGEX, message = "Invalid email")
    private String email;
    @Pattern(regexp = Constants.PHONE_REGEX, message = "Invalid phone number")
    private String phone;
    @Size(min = Constants.MIN_LENGTH_PASSWORD, max = Constants.MAX_LENGTH_PASSWORD, message = "The password must contain at least 6 characters and be up to 24 characters")
    private String password;
}
