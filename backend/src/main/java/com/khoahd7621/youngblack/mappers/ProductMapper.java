package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.product.CreateNewProductRequest;
import com.khoahd7621.youngblack.dtos.response.product.ProductAdminResponse;
import com.khoahd7621.youngblack.entities.Product;
import com.khoahd7621.youngblack.utils.SlugUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SlugUtil slugUtil;

    public Product toProduct(CreateNewProductRequest createNewProductRequest) {
        return Product.builder()
                .name(createNewProductRequest.getName())
                .description(createNewProductRequest.getDescription())
                .slug(slugUtil.getSlug(createNewProductRequest.getName()))
                .price(createNewProductRequest.getPrice())
                .primaryImageName(createNewProductRequest.getPrimaryImageName())
                .primaryImageUrl(createNewProductRequest.getPrimaryImageUrl())
                .secondaryImageName(createNewProductRequest.getSecondaryImageName())
                .secondaryImageUrl(createNewProductRequest.getSecondaryImageUrl())
                .createdAt(new Date())
                .updatedAt(new Date())
                .isVisible(createNewProductRequest.getIsVisible())
                .isDeleted(false)
                .category(categoryMapper.toCategory(createNewProductRequest.getCategory()))
                .build();
    }

    public ProductAdminResponse toProductAdminResponse(Product product) {
        boolean isPromotion = product.getEndDateDiscount() != null && product.getEndDateDiscount().after(new Date());
        return ProductAdminResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .slug(product.getSlug())
                .primaryImageUrl(product.getPrimaryImageUrl())
                .isPromotion(isPromotion)
                .isVisible(product.isVisible()).build();
    }
}
