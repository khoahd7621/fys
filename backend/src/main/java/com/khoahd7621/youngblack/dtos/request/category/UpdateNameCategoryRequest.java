package com.khoahd7621.youngblack.dtos.request.category;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNameCategoryRequest {
    @Min(value = 0, message = "Id must be equal or greater than 0")
    private Integer id;

    @NotBlank(message = "New name of category need to update is required")
    private String newName;
}
