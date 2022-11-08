package com.khoahd7621.youngblack.services.impl;

import com.khoahd7621.youngblack.dtos.request.product.CreateNewProductRequest;
import com.khoahd7621.youngblack.dtos.response.NoData;
import com.khoahd7621.youngblack.dtos.response.SuccessResponse;
import com.khoahd7621.youngblack.dtos.response.product.ListProductAdminWithPaginateResponse;
import com.khoahd7621.youngblack.dtos.response.product.ProductAdminResponse;
import com.khoahd7621.youngblack.dtos.response.productdetail.ProductDetailAdminResponse;
import com.khoahd7621.youngblack.entities.*;
import com.khoahd7621.youngblack.exceptions.BadRequestException;
import com.khoahd7621.youngblack.exceptions.NotFoundException;
import com.khoahd7621.youngblack.mappers.*;
import com.khoahd7621.youngblack.repositories.*;
import com.khoahd7621.youngblack.services.ProductAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
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
    private ProductDetailMapper productDetailMapper;
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
        List<VariantSize> listVariantSizeDBOptFindBySku = variantSizeRepository
                .findBySkuStartsWith(createNewProductRequest.getColors().get(0).getSku());
        if (listVariantSizeDBOptFindBySku.size() > 0) {
            throw new BadRequestException("Sku already exist");
        }

        Product product = productMapper.toProduct(createNewProductRequest);
        Product productDB = productRepository.save(product);

        List<ProductVariant> productVariants = createNewProductRequest.getColors().stream()
                .map(productVariantOfCreateNewProduct -> productVariantMapper.
                        toProductVariant(productVariantOfCreateNewProduct.getColor(), productDB))
                .collect(Collectors.toList());
        List<ProductVariant> productVariantsDB = productVariantRepository.saveAll(productVariants);

        List<Image> images = new ArrayList<>();
        List<VariantSize> variantSizes = new ArrayList<>();
        for (int i = 0; i < createNewProductRequest.getColors().size(); i++) {
            images.addAll(imageMapper.
                    toListImagesWithProductVariant(
                            createNewProductRequest.getColors().get(i).getImages(),
                            productVariantsDB.get(i)));
            variantSizes.addAll(variantSizeMapper.
                    toListVariantSizeWithProductVariant(
                            createNewProductRequest.getColors().get(i).getSizes(),
                            productVariantsDB.get(i),
                            createNewProductRequest.getColors().get(i).getSku(),
                            createNewProductRequest.getColors().get(i).getColor().getName()));
        }
        imageRepository.saveAll(images);
        variantSizeRepository.saveAll(variantSizes);
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

    @Override
    public SuccessResponse<ProductDetailAdminResponse> getProductDetailByProductId(Integer productId) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findByIsDeletedFalseAndId(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Don't exist product with this id.");
        }
        ProductDetailAdminResponse productDetailAdminResponse = productDetailMapper.toProductDetailAdminResponse(productOptional.get());
        return new SuccessResponse<>(productDetailAdminResponse, "Get product detail successfully.");
    }

    @Override
    public SuccessResponse<NoData> deleteProductByProductId(Integer productId) throws NotFoundException {
        Optional<Product> productOptional = productRepository.findByIsDeletedFalseAndId(productId);
        if (productOptional.isEmpty()) {
            throw new NotFoundException("Don't exist product with this id.");
        }
        Product product = productOptional.get();
        product.setDeleted(true);
        productRepository.save(product);
        return new SuccessResponse<>(NoData.builder().build(), "Delete product successfully.");
    }
}
