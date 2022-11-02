package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;

public interface ProductService {

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsWithPaginate(Integer offset, Integer limit);

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsByCategoryNameWithPaginate(String categoryName, Integer offset, Integer limit) throws BadRequestException;

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsSearchByNameWithPaginate(String query, Integer offset, Integer limit);

    public SuccessResponse<ListProductResponse> getNRelatedProductByCategoryId(int categoryId, int numberElements) throws NotFoundException;
}
