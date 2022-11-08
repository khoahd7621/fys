package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.request.product.CreateNewProductRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductAdminWithPaginateResponse;
import com.khoahd7621.youngblack.dtos.response.productdetail.ProductDetailAdminResponse;
import com.khoahd7621.youngblack.exceptions.BadRequestException;
import com.khoahd7621.youngblack.exceptions.NotFoundException;
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
    public SuccessResponse<NoData> createNewProduct(@Valid @RequestBody CreateNewProductRequest createNewProductRequest) throws BadRequestException {
        return productAdminService.createNewProduct(createNewProductRequest);
    }

    @GetMapping
    public SuccessResponse<ListProductAdminWithPaginateResponse> getAllProductWithPaginate(
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset
    ) {
        return productAdminService.getAllProductWithPaginate(limit, offset);
    }

    @GetMapping("/{productId}")
    public SuccessResponse<ProductDetailAdminResponse> getProductDetailByProductId(@PathVariable Integer productId)
            throws NotFoundException {
        return productAdminService.getProductDetailByProductId(productId);
    }

    @DeleteMapping("/{productId}")
    public SuccessResponse<NoData> deleteProductByProductId(@PathVariable Integer productId) throws NotFoundException {
        return productAdminService.deleteProductByProductId(productId);
    }
}
