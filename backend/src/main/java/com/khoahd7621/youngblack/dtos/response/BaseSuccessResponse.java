package com.khoahd7621.youngblack.dtos.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseSuccessResponse<T> {
    private final int code = 0;
    private String message;
    private T data;
}
