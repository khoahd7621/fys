package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.request.rating.CreateNewRatingRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;

public interface RatingService {
    public SuccessResponse<NoData> createNewRatingProductOfUser(CreateNewRatingRequest createNewRatingRequest) throws NotFoundException, BadRequestException;
}
