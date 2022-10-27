package com.khoahd7621.youngblack.dtos.request.size;


import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateNewSizeRequest {
    @NotEmpty(message = "Size is required")
    private String size;
}
