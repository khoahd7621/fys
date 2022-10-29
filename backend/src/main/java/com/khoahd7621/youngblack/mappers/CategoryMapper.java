package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.category.CategoryOfCreateNewProduct;
import com.khoahd7621.youngblack.dtos.request.category.CreateNewCategoryRequest;
import com.khoahd7621.youngblack.dtos.response.category.CategoryResponse;
import com.khoahd7621.youngblack.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category toCategory(CreateNewCategoryRequest createNewCategoryRequest) {
        return Category.builder().name(createNewCategoryRequest.getName()).build();
    }

    public Category toCategory(CategoryOfCreateNewProduct categoryOfCreateNewProduct) {
        return Category.builder()
                .id(categoryOfCreateNewProduct.getId())
                .name(categoryOfCreateNewProduct.getName()).build();
    }

    public CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
    }
}
