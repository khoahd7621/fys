package com.khoahd7621.youngblack.dtos.response.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class ProductResponse {
    private int productId;
    private String name;
    private String description;
    private long price;
    private long discountPrice;
    private Date startDateDiscount;
    private Date endDateDiscount;
    private String slug;
    private String primaryImageUrl;
    private String secondaryImageUrl;
    private boolean isPromotion;
    private String categoryName;
}
