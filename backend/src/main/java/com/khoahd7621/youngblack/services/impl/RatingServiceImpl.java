package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.request.rating.CreateNewRatingRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.entities.Product;
import com.khoahd7621.youngblack.entities.Rating;
import com.khoahd7621.youngblack.entities.User;
import com.khoahd7621.youngblack.entities.compositekey.RatingKey;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.mappers.RatingMapper;
import com.khoahd7621.youngblack.repositories.ProductRepository;
import com.khoahd7621.youngblack.repositories.RatingRepository;
import com.khoahd7621.youngblack.services.AuthService;
import com.khoahd7621.youngblack.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AuthService authService;
    @Autowired
    private RatingMapper ratingMapper;

    @Override
    public SuccessResponse<NoData> createNewRatingProductOfUser(CreateNewRatingRequest createNewRatingRequest) throws NotFoundException, BadRequestException {
        Optional<Product> productOptional = productRepository.findByIsDeletedFalseAndId(createNewRatingRequest.getProductId());
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Don't exist product with this id.");
        }
        User user = authService.getUserLoggedIn();
        Optional<Rating> ratingOptional = ratingRepository.findByRatingId(RatingKey.builder().userId(user.getId())
                .productId(productOptional.get().getId()).build());
        if (ratingOptional.isPresent()) {
            throw new BadRequestException("You already rated this product.");
        }
        Rating rating = ratingMapper.toRating(createNewRatingRequest, user, productOptional.get());
        ratingRepository.save(rating);
        return new SuccessResponse<>(NoData.builder().build(), "Create new rating successfully.");
    }
}
