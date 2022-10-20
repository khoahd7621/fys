package com.khoahd7621.youngblack.models;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class BaseResponse {
    private Map<String, Object> response;

    public BaseResponse(int statusCode, Object data, String statusMessage) {
        this.response = new HashMap<>();
        this.response.put("EC", statusCode);
        this.response.put("DT", data);
        this.response.put("EM", statusMessage);
    }
}
