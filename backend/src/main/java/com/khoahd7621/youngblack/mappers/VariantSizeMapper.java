package com.khoahd7621.youngblack.mappers;

import com.khoahd7621.youngblack.dtos.request.size.SizeOfCreateNewProduct;
import com.khoahd7621.youngblack.entities.ProductVariant;
import com.khoahd7621.youngblack.entities.VariantSize;
import com.khoahd7621.youngblack.utils.SkuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariantSizeMapper {

    @Autowired
    private SizeMapper sizeMapper;
    @Autowired
    private SkuUtil skuUtil;

    public List<VariantSize> toListVariantSizeWithProductVariant(List<SizeOfCreateNewProduct> sizes, ProductVariant productVariant, String rootSku, String colorName) {
        return sizes.stream().map(sizeOfCreateNewProduct ->
                        VariantSize.builder()
                                .sku(skuUtil.getProductSku(rootSku, colorName, sizeOfCreateNewProduct.getSize()))
                                .isInStock(true)
                                .size(sizeMapper.toSize(sizeOfCreateNewProduct))
                                .productVariant(productVariant).build())
                .collect(Collectors.toList());
    }
}
