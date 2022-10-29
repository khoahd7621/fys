package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.image.ImageOfCreateNewProduct;
import com.khoahd7621.youngblack.entities.Image;
import com.khoahd7621.youngblack.entities.ProductVariant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {
    public List<Image> toListImagesWithProductVariant(List<ImageOfCreateNewProduct> images, ProductVariant productVariant) {
        return images.stream().map((image) -> Image.builder()
                .name(image.getImageName())
                .imageUrl(image.getImageUrl())
                .productVariant(productVariant).build()).collect(Collectors.toList());
    }
}
