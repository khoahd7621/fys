package com.khoahd7621.youngblack.dtos.user.dto;

import com.khoahd7621.youngblack.constants.Constants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class UserDTOUpdateRequest {
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Phone is required")
    @Pattern(regexp = Constants.PHONE_REGEX, message = "Invalid phone number")
    private String phone;
    @NotBlank(message = "Address is required")
    private String address;
}
