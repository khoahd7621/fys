package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.request.category.CreateNewCategoryRequest;
import com.khoahd7621.youngblack.dtos.request.category.UpdateNameCategoryRequest;
import com.khoahd7621.youngblack.dtos.response.ExceptionResponse;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.category.CategoryResponse;
import com.khoahd7621.youngblack.dtos.response.category.ListCategoriesResponse;
import com.khoahd7621.youngblack.entities.Category;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.mappers.CategoryMapper;
import com.khoahd7621.youngblack.repositories.CategoryRepository;
import com.khoahd7621.youngblack.services.CategoryAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryAdminServiceImpl implements CategoryAdminService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public SuccessResponse<CategoryResponse> createNewCategory(CreateNewCategoryRequest createNewCategoryRequest)
            throws CustomBadRequestException {
        Optional<Category> categoryOptional = categoryRepository.findByName(createNewCategoryRequest.getName());
        if (categoryOptional.isPresent()) {
            throw new CustomBadRequestException(ExceptionResponse.builder().code(-1).message("This category already existed.").build());
        }
        Category category = categoryMapper.toCategory(createNewCategoryRequest);
        Category result = categoryRepository.save(category);
        return new SuccessResponse<>(categoryMapper.toCategoryResponse(result), "Create new category successfully.");
    }

    @Override
    public SuccessResponse<ListCategoriesResponse> getAllCategory() {
        List<CategoryResponse> categoryResponseList = categoryRepository.findAll()
                .stream().map(categoryMapper::toCategoryResponse).collect(Collectors.toList());
        ListCategoriesResponse listCategoriesResponse =
                ListCategoriesResponse.builder().categories(categoryResponseList).build();
        return new SuccessResponse<>(listCategoriesResponse, "Get list category success.");
    }

    @Override
    public SuccessResponse<CategoryResponse> updateNameCategory(UpdateNameCategoryRequest updateNameCategoryRequest) throws CustomBadRequestException {
        Optional<Category> categoryOptionalFindById = categoryRepository.findById(updateNameCategoryRequest.getId());
        if (categoryOptionalFindById.isEmpty()) {
            throw new CustomBadRequestException(ExceptionResponse.builder()
                    .code(-1).message("Id of category is not exist.").build());
        }
        if (categoryOptionalFindById.get().getName().equals(updateNameCategoryRequest.getNewName())) {
            throw new CustomBadRequestException(ExceptionResponse.builder()
                    .code(-1).message("New category name is the same with old category name. Nothing update.").build());
        }
        Optional<Category> categoryOptionalFindByName = categoryRepository.findByName(updateNameCategoryRequest.getNewName());
        if (categoryOptionalFindByName.isPresent()) {
            throw new CustomBadRequestException(ExceptionResponse.builder()
                    .code(-1).message("This category name already exist.").build());
        }
        Category category = categoryOptionalFindById.get();
        category.setName(updateNameCategoryRequest.getNewName());
        categoryRepository.save(category);
        return new SuccessResponse<>(categoryMapper.toCategoryResponse(category), "Update name category success.");
    }
}
