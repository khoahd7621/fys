package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;

public interface ProductService {

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsWithPaginate(Integer offset, Integer limit);

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsByCategoryNameWithPaginate(String categoryName, Integer offset, Integer limit) throws CustomBadRequestException;
}
