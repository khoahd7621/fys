package com.khoahd7621.youngblack.dtos.request.product;

import com.khoahd7621.youngblack.dtos.request.category.CategoryOfCreateNewProduct;
import com.khoahd7621.youngblack.dtos.request.productvariant.ProductVariantOfCreateNewProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateNewProductRequest {
    private String name;
    private Long price;
    private String description;
    private Boolean isVisible;
    private String primaryImageName;
    private String primaryImageUrl;
    private String secondaryImageName;
    private String secondaryImageUrl;

    private CategoryOfCreateNewProduct category;
    private List<ProductVariantOfCreateNewProduct> colors;
}
