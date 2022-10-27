package com.khoahd7621.youngblack.dtos.request.category;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteCategoryRequest {
    @NotBlank(message = "Id of category need to delete is required")
    private int id;
}
