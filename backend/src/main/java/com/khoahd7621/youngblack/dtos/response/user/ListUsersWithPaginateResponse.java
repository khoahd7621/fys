package com.khoahd7621.youngblack.dtos.response.user;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListUsersWithPaginateResponse {
    private long totalRows;
    private int totalPages;
    private List<UserResponse> listUsers;
}
