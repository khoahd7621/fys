package com.khoahd7621.youngblack.models.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenPayload {
    private long id;
    private String email;
    private String phone;
}
