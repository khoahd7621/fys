package com.khoahd7621.youngblack.controllers;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsWithPaginate(
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        return productService.getAllProductsWithPaginate(offset, limit);
    }

    @GetMapping
    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsByCategoryNameWithPaginate(
            @RequestParam(name = "category-name") String categoryName,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) throws CustomBadRequestException {
        return productService.getAllProductsByCategoryNameWithPaginate(categoryName, offset, limit);
    }
}
