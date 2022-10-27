package com.khoahd7621.youngblack.dtos.request.color;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorNameRequest {
    @Min(value = 0, message = "Id must be equal or greater than 0")
    private Integer id;
    @NotEmpty(message = "New name of color is required")
    private String newName;
}
