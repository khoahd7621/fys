package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.request.color.CreateNewColorRequest;
import com.khoahd7621.youngblack.dtos.request.color.UpdateColorNameRequest;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.color.ColorResponse;
import com.khoahd7621.youngblack.dtos.response.color.ListColorsResponse;
import com.khoahd7621.youngblack.entities.Color;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.mappers.ColorMapper;
import com.khoahd7621.youngblack.repositories.ColorRepository;
import com.khoahd7621.youngblack.services.ColorAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ColorAdminServiceImpl implements ColorAdminService {

    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ColorMapper colorMapper;

    @Override
    public SuccessResponse<ColorResponse> createNewColor(CreateNewColorRequest createNewColorRequest) throws CustomBadRequestException {
        Optional<Color> colorOptional = colorRepository.findByName(createNewColorRequest.getName());
        if (colorOptional.isPresent()) {
            throw new CustomBadRequestException("This color already existed.");
        }
        Color color = colorMapper.toColor(createNewColorRequest);
        Color result = colorRepository.save(color);
        return new SuccessResponse<>(colorMapper.toColorResponse(result), "Create new color successfully.");
    }

    @Override
    public SuccessResponse<ListColorsResponse> getAllColors() {
        List<ColorResponse> sizeResponseList = colorRepository.findAll()
                .stream().map(colorMapper::toColorResponse).collect(Collectors.toList());
        ListColorsResponse listColorsResponse =
                ListColorsResponse.builder().colors(sizeResponseList).build();
        return new SuccessResponse<>(listColorsResponse, "Get list color success.");
    }

    @Override
    public SuccessResponse<ColorResponse> updateColorName(UpdateColorNameRequest updateColorNameRequest) throws CustomBadRequestException {
        Optional<Color> colorOptionalFindById = colorRepository.findById(updateColorNameRequest.getId());
        if (colorOptionalFindById.isEmpty()) {
            throw new CustomBadRequestException("Id of color is not exist.");
        }
        if (colorOptionalFindById.get().getName().equals(updateColorNameRequest.getNewName())) {
            throw new CustomBadRequestException("New color is the same with old color. Nothing update.");
        }
        Optional<Color> colorOptionalFindByName = colorRepository.findByName(updateColorNameRequest.getNewName());
        if (colorOptionalFindByName.isPresent()) {
            throw new CustomBadRequestException("This color already exist.");
        }
        Color color = colorOptionalFindById.get();
        color.setName(updateColorNameRequest.getNewName());
        colorRepository.save(color);
        return new SuccessResponse<>(colorMapper.toColorResponse(color), "Update size success.");
    }
}
