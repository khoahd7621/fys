package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.size.CreateNewSizeRequest;
import com.khoahd7621.youngblack.dtos.request.size.SizeOfCreateNewProduct;
import com.khoahd7621.youngblack.dtos.response.size.SizeResponse;
import com.khoahd7621.youngblack.entities.Size;
import org.springframework.stereotype.Component;

@Component
public class SizeMapper {

    public Size toSize(CreateNewSizeRequest createNewSizeRequest) {
        return Size.builder().size(createNewSizeRequest.getSize()).build();
    }

    public Size toSize(SizeOfCreateNewProduct sizeOfCreateNewProduct) {
        return Size.builder().id(sizeOfCreateNewProduct.getId()).size(sizeOfCreateNewProduct.getSize()).build();
    }


    public SizeResponse toSizeResponse(Size size) {
        return SizeResponse.builder().id(size.getId()).size(size.getSize()).build();
    }
}
