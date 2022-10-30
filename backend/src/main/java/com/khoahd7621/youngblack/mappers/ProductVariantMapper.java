package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.color.ColorOfCreateNewProduct;
import com.khoahd7621.youngblack.entities.Product;
import com.khoahd7621.youngblack.entities.ProductVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductVariantMapper {

    @Autowired
    private ColorMapper colorMapper;

    public ProductVariant toProductVariant(ColorOfCreateNewProduct colorOfCreateNewProduct, Product product) {
        return ProductVariant.builder()
                .color(colorMapper.toColor(colorOfCreateNewProduct))
                .product(product).build();
    }
}
