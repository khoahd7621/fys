package com.khoahd7621.youngblack.exceptions.custom;

import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomException extends Exception {
    private ExceptionResponse error;
}
