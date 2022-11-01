package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;

public interface ProductService {

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsWithPaginate(Integer offset, Integer limit);

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsByCategoryNameWithPaginate(String categoryName, Integer offset, Integer limit) throws CustomBadRequestException;

    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsSearchByNameWithPaginate(String query, Integer offset, Integer limit);

    public SuccessResponse<ListProductResponse> getNRelatedProductByCategoryId(int categoryId, int numberElements) throws CustomNotFoundException;
}
