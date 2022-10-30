package com.khoahd7621.youngblack.dtos.response.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductAdminResponse {
    private int productId;
    private String name;
    private String description;
    private long price;
    private String slug;
    private String primaryImageUrl;
    private boolean isPromotion;
    private boolean isVisible;
}
