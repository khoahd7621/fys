package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.request.category.CreateNewCategoryRequest;
import com.khoahd7621.youngblack.dtos.request.category.UpdateNameCategoryRequest;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.category.CategoryResponse;
import com.khoahd7621.youngblack.dtos.response.category.ListCategoriesResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.services.CategoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/category")
public class CategoryAdminController {

    @Autowired
    private CategoryAdminService categoryAdminService;

    @PostMapping
    public SuccessResponse<CategoryResponse> createNewCategory(@Valid @RequestBody CreateNewCategoryRequest createNewCategoryRequest) throws BadRequestException {
        return categoryAdminService.createNewCategory(createNewCategoryRequest);
    }

    @GetMapping
    public SuccessResponse<ListCategoriesResponse> getAllCategory() {
        return categoryAdminService.getAllCategory();
    }

    @PutMapping
    public SuccessResponse<CategoryResponse> updateNameCategory(@Valid @RequestBody UpdateNameCategoryRequest updateNameCategoryRequest) throws BadRequestException {
        return categoryAdminService.updateNameCategory(updateNameCategoryRequest);
    }

}
