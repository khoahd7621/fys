package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.request.product.CreateNewProductRequest;
import com.khoahd7621.youngblack.dtos.request.productvariant.ProductVariantOfCreateNewProduct;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductAdminWithPaginateResponse;
import com.khoahd7621.youngblack.dtos.response.product.ProductAdminResponse;
import com.khoahd7621.youngblack.entities.Image;
import com.khoahd7621.youngblack.entities.Product;
import com.khoahd7621.youngblack.entities.ProductVariant;
import com.khoahd7621.youngblack.entities.VariantSize;
import com.khoahd7621.youngblack.exceptions.custom.BadRequestException;
import com.khoahd7621.youngblack.mappers.ImageMapper;
import com.khoahd7621.youngblack.mappers.ProductMapper;
import com.khoahd7621.youngblack.mappers.ProductVariantMapper;
import com.khoahd7621.youngblack.mappers.VariantSizeMapper;
import com.khoahd7621.youngblack.repositories.ImageRepository;
import com.khoahd7621.youngblack.repositories.ProductRepository;
import com.khoahd7621.youngblack.repositories.ProductVariantRepository;
import com.khoahd7621.youngblack.repositories.VariantSizeRepository;
import com.khoahd7621.youngblack.services.ProductAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductAdminServiceImpl implements ProductAdminService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private VariantSizeRepository variantSizeRepository;
    @Autowired
    private ProductVariantRepository productVariantRepository;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductVariantMapper productVariantMapper;
    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private VariantSizeMapper variantSizeMapper;

    @Override
    public SuccessResponse<NoData> createNewProduct(CreateNewProductRequest createNewProductRequest) throws BadRequestException {
        Optional<Product> productDBOptFindByName = productRepository.findByName(createNewProductRequest.getName().toUpperCase());
        if (productDBOptFindByName.isPresent()) {
            throw new BadRequestException("Product name already exist");
        }
        List<VariantSize> listVariantSizeDBOptFindBySku = variantSizeRepository.findBySkuStartsWith(createNewProductRequest.getColors().get(0).getSku());
        if (listVariantSizeDBOptFindBySku.size() > 0) {
            throw new BadRequestException("Sku already exist");
        }

        Product product = productMapper.toProduct(createNewProductRequest);
        Product productDB = productRepository.save(product);

        for (ProductVariantOfCreateNewProduct productVariantOfCreateNewProduct : createNewProductRequest.getColors()) {
            ProductVariant productVariant = productVariantMapper.
                    toProductVariant(productVariantOfCreateNewProduct.getColor(), productDB);
            ProductVariant productVariantDB = productVariantRepository.save(productVariant);

            List<Image> imageList = imageMapper.
                    toListImagesWithProductVariant(productVariantOfCreateNewProduct.getImages(), productVariantDB);
            List<VariantSize> variantSizeList = variantSizeMapper.
                    toListVariantSizeWithProductVariant(
                            productVariantOfCreateNewProduct.getSizes(),
                            productVariantDB,
                            productVariantOfCreateNewProduct.getSku(),
                            productVariantOfCreateNewProduct.getColor().getName());
            imageRepository.saveAll(imageList);
            variantSizeRepository.saveAll(variantSizeList);
        }
        return new SuccessResponse<>(NoData.builder().build(), "Create new product successfully.");
    }

    @Override
    public SuccessResponse<ListProductAdminWithPaginateResponse> getAllProductWithPaginate(int limit, int offset) {
        Page<Product> productPage = productRepository.findAll(PageRequest.of(offset, limit, Sort.by("id").descending()));
        List<ProductAdminResponse> productAdminResponseList = productPage.stream()
                .filter(product -> !product.isDeleted())
                .map(productMapper::toProductAdminResponse).collect(Collectors.toList());
        ListProductAdminWithPaginateResponse listProductAdminWithPaginateResponse =
                ListProductAdminWithPaginateResponse.builder()
                        .products(productAdminResponseList)
                        .totalRows(productPage.getTotalElements())
                        .totalPages(productPage.getTotalPages()).build();
        return new SuccessResponse<>(listProductAdminWithPaginateResponse, "Get list products success!");
    }
}
