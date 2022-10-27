package com.khoahd7621.youngblack.dtos.request.color;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewColorRequest {
    @NotEmpty(message = "Color name is required")
    private String name;
}
