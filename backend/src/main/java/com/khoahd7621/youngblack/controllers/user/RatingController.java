package com.khoahd7621.youngblack.controllers.user;

import com.khoahd7621.youngblack.dtos.request.rating.CreateNewRatingRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.rating.ListRatingsWithPaginateResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public SuccessResponse<NoData> createNewRatingProductOfUser(
            @Valid @RequestBody CreateNewRatingRequest createNewRatingRequest) throws NotFoundException, BadRequestException {
        return ratingService.createNewRatingProductOfUser(createNewRatingRequest);
    }

    @GetMapping
    public SuccessResponse<ListRatingsWithPaginateResponse> getAllRatingsOfProductWithPaginateAndSort(
            @RequestParam(name = "product-id") Integer productId,
            @RequestParam(name = "offset", defaultValue = "0") Integer offset,
            @RequestParam(name = "limit", defaultValue = "20") Integer limit,
            @RequestParam(name = "sort-type", defaultValue = "DESC") String sortType
    ) throws BadRequestException, NotFoundException {
        return ratingService.getAllRatingsOfProductWithPaginateAndSort(productId, offset, limit, sortType);
    }
}
