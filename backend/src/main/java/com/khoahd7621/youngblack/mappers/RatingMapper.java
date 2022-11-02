package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.rating.CreateNewRatingRequest;
import com.khoahd7621.youngblack.entities.Product;
import com.khoahd7621.youngblack.entities.Rating;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.entities.compositekey.RatingKey;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class RatingMapper {

    public Rating toRating(CreateNewRatingRequest createNewRatingRequest, User user, Product product) {
        return Rating.builder()
                .ratingId(RatingKey.builder().userId(user.getId()).productId(product.getId()).build())
                .user(user)
                .product(product)
                .stars(createNewRatingRequest.getStar())
                .title(createNewRatingRequest.getTitle())
                .comment(createNewRatingRequest.getContent())
                .isShow(true)
                .createdDate(new Date()).build();
    }
}
