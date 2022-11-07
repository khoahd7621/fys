package com.khoahd7621.youngblack.dtos.response;

import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    private final int code = 0;
    @Schema(anyOf = {UserResponse.class, NoData.class})
    private T data;
    private String message;
}
