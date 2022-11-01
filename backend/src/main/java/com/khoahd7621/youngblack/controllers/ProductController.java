package com.khoahd7621.youngblack.controllers;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/product")
@Validated
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

    @GetMapping("/search")
    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsSearchByNameWithPaginate(
            @RequestParam(name = "query", defaultValue = "") String query,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        return productService.getAllProductsSearchByNameWithPaginate(query, offset, limit);
    }

    @GetMapping("/related")
    public SuccessResponse<ListProductResponse> getNRelatedProductByCategoryId(
            @RequestParam(name = "categoryId") @Min(value = 0, message = "Min number of category id is 0") Integer categoryId,
            @RequestParam(name = "numberElements") @Min(value = 1, message = "Min number of elements is 1") Integer numberElements
    ) throws CustomNotFoundException {
        return productService.getNRelatedProductByCategoryId(categoryId, numberElements);
    }
}
