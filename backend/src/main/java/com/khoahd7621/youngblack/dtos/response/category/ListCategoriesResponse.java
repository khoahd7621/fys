package com.khoahd7621.youngblack.dtos.response.category;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCategoriesResponse {
    List<CategoryResponse> categories;
}
