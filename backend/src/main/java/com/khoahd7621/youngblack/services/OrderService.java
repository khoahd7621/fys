package com.khoahd7621.youngblack.services;

import com.khoahd7621.youngblack.dtos.request.order.CreateNewOrderRequest;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.order.CreateNewOrderResponse;
import com.khoahd7621.youngblack.exceptions.NotFoundException;

public interface OrderService {
    public SuccessResponse<CreateNewOrderResponse> createNewOrder(CreateNewOrderRequest createNewOrderRequest) throws NotFoundException;
}
