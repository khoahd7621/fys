package com.khoahd7621.youngblack.controllers;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
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
    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsWithPaginateAndSort(
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "sort-base", defaultValue = "id") String sortBase,
            @RequestParam(name = "sort-type", defaultValue = "DESC") String sortType
    ) throws BadRequestException {
        return productService.getAllProductsWithPaginateAndSort(offset, limit, sortBase, sortType);
    }

    @GetMapping
    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsByCategoryNameWithPaginateAndSort(
            @RequestParam(name = "category-name") String categoryName,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "sort-base", defaultValue = "id") String sortBase,
            @RequestParam(name = "sort-type", defaultValue = "DESC") String sortType
    ) throws BadRequestException {
        return productService.getAllProductsByCategoryNameWithPaginateAndSort(categoryName, offset, limit, sortBase, sortType);
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
    ) throws NotFoundException {
        return productService.getNRelatedProductByCategoryId(categoryId, numberElements);
    }
}
