package com.khoahd7621.youngblack.dtos.request.color;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class CreateNewColorRequest {
    @NotEmpty(message = "Color name is required")
    private String name;
}
