package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.request.product.CreateNewProductRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductAdminWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;

public interface ProductAdminService {
    public SuccessResponse<NoData> createNewProduct(CreateNewProductRequest createNewProductRequest) throws CustomBadRequestException;

    public SuccessResponse<ListProductAdminWithPaginateResponse> getAllProductWithPaginate(int limit, int offset);
}