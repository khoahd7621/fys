package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.request.product.CreateNewProductRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductAdminWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.services.ProductAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/product")
public class ProductAdminController {

    @Autowired
    private ProductAdminService productAdminService;

    @PostMapping
    public SuccessResponse<NoData> createNewProduct(@Valid @RequestBody CreateNewProductRequest createNewProductRequest) throws CustomBadRequestException {
        return productAdminService.createNewProduct(createNewProductRequest);
    }

    @GetMapping
    public SuccessResponse<ListProductAdminWithPaginateResponse> getAllProductWithPaginate(
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        return productAdminService.getAllProductWithPaginate(limit, offset);
    }
}