package com.khoahd7621.youngblack.dtos.response.color;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListColorsResponse {
    List<ColorResponse> colors;
}
