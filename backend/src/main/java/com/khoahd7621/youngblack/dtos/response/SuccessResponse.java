package com.khoahd7621.youngblack.dtos.response;

import com.khoahd7621.youngblack.dtos.response.product.ListProductAdminWithPaginateResponse;
import com.khoahd7621.youngblack.dtos.response.productdetail.ProductDetailAdminResponse;
import com.khoahd7621.youngblack.dtos.response.size.ListSizesResponse;
import com.khoahd7621.youngblack.dtos.response.size.SizeResponse;
import com.khoahd7621.youngblack.dtos.response.user.ListUsersWithPaginateResponse;
import com.khoahd7621.youngblack.dtos.response.user.UserResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse<T> {
    private final int code = 0;
    @Schema(anyOf = {
            UserResponse.class,
            NoData.class,
            ListUsersWithPaginateResponse.class,
            SizeResponse.class,
            ListSizesResponse.class,
            ListProductAdminWithPaginateResponse.class,
            ProductDetailAdminResponse.class
    })
    private T data;
    private String message;
}
