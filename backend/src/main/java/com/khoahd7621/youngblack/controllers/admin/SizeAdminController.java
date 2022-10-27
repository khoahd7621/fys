package com.khoahd7621.youngblack.controllers.admin;

import com.khoahd7621.youngblack.dtos.request.size.CreateNewSizeRequest;
import com.khoahd7621.youngblack.dtos.request.size.UpdateSizeRequest;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.size.ListSizesResponse;
import com.khoahd7621.youngblack.dtos.response.size.SizeResponse;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
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
    public SuccessResponse<SizeResponse> createNewSize(@Valid @RequestBody CreateNewSizeRequest createNewSizeRequest) throws CustomBadRequestException {
        return sizeAdminService.createNewSize(createNewSizeRequest);
    }

    @GetMapping
    private SuccessResponse<ListSizesResponse> getAllSize() {
        return sizeAdminService.getAllSize();
    }

    @PutMapping
    private SuccessResponse<SizeResponse> updateSize(@Valid @RequestBody UpdateSizeRequest updateSizeRequest) throws CustomBadRequestException {
        return sizeAdminService.updateSize(updateSizeRequest);
    }
}
