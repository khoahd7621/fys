package com.khoahd7621.youngblack.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    private final int code = 0;
    private T data;
    private String message;
}
