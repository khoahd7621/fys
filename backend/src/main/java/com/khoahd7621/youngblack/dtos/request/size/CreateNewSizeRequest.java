package com.khoahd7621.youngblack.dtos.request.size;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
public class CreateNewSizeRequest {
    @NotEmpty(message = "Size is required")
    private String size;
}
