package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.request.size.CreateNewSizeRequest;
import com.khoahd7621.youngblack.dtos.request.size.UpdateSizeRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.size.ListSizesResponse;
import com.khoahd7621.youngblack.dtos.response.size.SizeResponse;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.exceptions.custom.NotFoundException;
import com.khoahd7621.youngblack.services.SizeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/size")
public class SizeAdminController {

    @Autowired
    private SizeAdminService sizeAdminService;

    @PostMapping
    public SuccessResponse<SizeResponse> createNewSize(@Valid @RequestBody CreateNewSizeRequest createNewSizeRequest) throws BadRequestException {
        return sizeAdminService.createNewSize(createNewSizeRequest);
    }

    @GetMapping
    private SuccessResponse<ListSizesResponse> getAllSize() {
        return sizeAdminService.getAllSize();
    }

    @PutMapping
    private SuccessResponse<SizeResponse> updateSize(@Valid @RequestBody UpdateSizeRequest updateSizeRequest) throws BadRequestException {
        return sizeAdminService.updateSize(updateSizeRequest);
    }

    @DeleteMapping("/{sizeId}")
    public SuccessResponse<NoData> deleteSize(@PathVariable Integer sizeId) throws NotFoundException, BadRequestException {
        return sizeAdminService.deleteSize(sizeId);
    }
}
