package com.khoahd7621.youngblack.controllers.user;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.dtos.request.user.UserChangePasswordRequest;
import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import com.khoahd7621.youngblack.dtos.request.user.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get current user information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get current user information success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuccessResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @GetMapping("/current")
    public SuccessResponse<UserResponse> getCurrentUser() throws NotFoundException {
        return userService.getCurrentUser();
    }

    @Operation(summary = "Update user's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update user's information success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuccessResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Duplicate phone number",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @PutMapping
    public SuccessResponse<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) throws BadRequestException, NotFoundException {
        return userService.updateUser(userUpdateRequest);
    }

    @Operation(summary = "Change user's password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Change password success",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SuccessResponse.class))}),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Change password fail",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))})
    })
    @PutMapping("/change-password")
    public SuccessResponse<NoData> changePassword(@Valid @RequestBody UserChangePasswordRequest userChangePasswordRequest) throws BadRequestException, NotFoundException {
        return userService.changePassword(userChangePasswordRequest);
    }

}
