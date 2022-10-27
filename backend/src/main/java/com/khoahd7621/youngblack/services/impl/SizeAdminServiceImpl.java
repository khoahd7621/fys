package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.request.size.CreateNewSizeRequest;
import com.khoahd7621.youngblack.dtos.request.size.UpdateSizeRequest;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.size.ListSizesResponse;
import com.khoahd7621.youngblack.dtos.response.size.SizeResponse;
import com.khoahd7621.youngblack.entities.Size;
import com.khoahd7621.youngblack.exceptions.custom.CustomBadRequestException;
import com.khoahd7621.youngblack.mappers.SizeMapper;
import com.khoahd7621.youngblack.repositories.SizeRepository;
import com.khoahd7621.youngblack.services.SizeAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SizeAdminServiceImpl implements SizeAdminService {

    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private SizeMapper sizeMapper;

    @Override
    public SuccessResponse<SizeResponse> createNewSize(CreateNewSizeRequest createNewSizeRequest) throws CustomBadRequestException {
        Optional<Size> sizeOptional = sizeRepository.findBySize(createNewSizeRequest.getSize());
        if (sizeOptional.isPresent()) {
            throw new CustomBadRequestException("This size already existed.");
        }
        Size size = sizeMapper.toSize(createNewSizeRequest);
        Size result = sizeRepository.save(size);
        return new SuccessResponse<>(sizeMapper.toSizeResponse(result), "Create new size successfully.");
    }

    @Override
    public SuccessResponse<ListSizesResponse> getAllSize() {
        List<SizeResponse> sizeResponseList = sizeRepository.findAll()
                .stream().map(sizeMapper::toSizeResponse).collect(Collectors.toList());
        ListSizesResponse listSizesResponse =
                ListSizesResponse.builder().sizes(sizeResponseList).build();
        return new SuccessResponse<>(listSizesResponse, "Get list size success.");
    }

    @Override
    public SuccessResponse<SizeResponse> updateSize(UpdateSizeRequest updateSizeRequest) throws CustomBadRequestException {
        Optional<Size> sizeOptionalFindById = sizeRepository.findById(updateSizeRequest.getId());
        if (sizeOptionalFindById.isEmpty()) {
            throw new CustomBadRequestException("Id of size is not exist.");
        }
        if (sizeOptionalFindById.get().getSize().equals(updateSizeRequest.getNewSize())) {
            throw new CustomBadRequestException("New size is the same with old size. Nothing update.");
        }
        Optional<Size> sizeOptionalFindBySize = sizeRepository.findBySize(updateSizeRequest.getNewSize());
        if (sizeOptionalFindBySize.isPresent()) {
            throw new CustomBadRequestException("This category name already exist.");
        }
        Size size = sizeOptionalFindById.get();
        size.setSize(updateSizeRequest.getNewSize());
        sizeRepository.save(size);
        return new SuccessResponse<>(sizeMapper.toSizeResponse(size), "Update size success.");
    }
}
