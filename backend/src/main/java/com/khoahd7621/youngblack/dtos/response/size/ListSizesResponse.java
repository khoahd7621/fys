package com.khoahd7621.youngblack.dtos.response.size;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListSizesResponse {
    private List<SizeResponse> sizes;
}
