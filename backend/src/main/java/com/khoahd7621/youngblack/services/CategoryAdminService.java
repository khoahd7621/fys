package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.request.category.CreateNewCategoryRequest;
import com.khoahd7621.youngblack.dtos.request.category.UpdateNameCategoryRequest;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.category.CategoryResponse;
import com.khoahd7621.youngblack.dtos.response.category.ListCategoriesResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;

public interface CategoryAdminService {

    public SuccessResponse<CategoryResponse> createNewCategory(CreateNewCategoryRequest createNewCategoryRequest) throws BadRequestException;

    public SuccessResponse<ListCategoriesResponse> getAllCategory();

    public SuccessResponse<CategoryResponse> updateNameCategory(UpdateNameCategoryRequest updateNameCategoryRequest) throws BadRequestException;

}
