package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.request.color.CreateNewColorRequest;
import com.khoahd7621.youngblack.dtos.request.color.UpdateColorNameRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.color.ColorResponse;
import com.khoahd7621.youngblack.dtos.response.color.ListColorsResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.services.ColorAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/color")
public class ColorAdminController {

    @Autowired
    private ColorAdminService colorAdminService;

    @PostMapping
    public SuccessResponse<ColorResponse> createNewColor(@Valid @RequestBody CreateNewColorRequest createNewColorRequest)
            throws BadRequestException {
        return colorAdminService.createNewColor(createNewColorRequest);
    }

    @GetMapping
    private SuccessResponse<ListColorsResponse> getAllColors() {
        return colorAdminService.getAllColors();
    }

    @PutMapping
    private SuccessResponse<ColorResponse> updateColorName(@Valid @RequestBody UpdateColorNameRequest updateColorNameRequest)
            throws BadRequestException {
        return colorAdminService.updateColorName(updateColorNameRequest);
    }

    @DeleteMapping
    public SuccessResponse<NoData> deleteColor(@RequestBody Integer colorId) throws NotFoundException, BadRequestException {
        return colorAdminService.deleteColor(colorId);
    }
}
