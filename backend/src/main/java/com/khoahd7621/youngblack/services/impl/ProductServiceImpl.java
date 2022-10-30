package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductWithPaginateResponse;
import com.khoahd7621.youngblack.dtos.response.product.ProductResponse;
import com.khoahd7621.youngblack.entities.Product;
import com.khoahd7621.youngblack.mappers.ProductMapper;
import com.khoahd7621.youngblack.repositories.ProductRepository;
import com.khoahd7621.youngblack.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public SuccessResponse<ListProductWithPaginateResponse> getAllProductsWithPaginate(Integer offset, Integer limit) {
        Page<Product> productPage = productRepository.findAllByIsDeletedFalse(PageRequest.of(offset, limit));
        List<ProductResponse> productResponseList =
                productPage.stream().map(productMapper::toProductResponse).collect(Collectors.toList());
        ListProductWithPaginateResponse listProductWithPaginateResponse =
                ListProductWithPaginateResponse.builder()
                        .products(productResponseList)
                        .totalRows(productPage.getTotalElements())
                        .totalPages(productPage.getTotalPages()).build();
        return new SuccessResponse<>(listProductWithPaginateResponse, "Get list products success.");
    }

}
