package com.khoahd7621.youngblack.dtos.request.size;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSizeRequest {
    @Min(value = 0, message = "Id must be equal or greater than 0")
    private Integer id;
    @NotEmpty(message = "Size is required")
    private String newSize;
}
