package com.khoahd7621.youngblack.controllers;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.productdetail.ProductDetailResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomNotFoundException;
import com.khoahd7621.youngblack.services.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product-detail")
public class ProductDetailController {

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping
    public SuccessResponse<ProductDetailResponse> getProductDetailBySlug(@RequestParam(name = "slug") String slug)
            throws CustomNotFoundException {
        return productDetailService.getProductDetailBySlug(slug);
    }

}
