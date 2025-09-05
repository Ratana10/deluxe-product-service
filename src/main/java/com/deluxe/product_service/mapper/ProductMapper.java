package com.deluxe.product_service.mapper;

import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "productColors", ignore = true)
    Product toEntity(ProductRequest request);

    @Mapping(target = "productColors", source = "productColors")
    ProductResponse toResponse(Product product);

    @Mapping(target = "productColors", ignore = true)
    void updateProductFromRequest(ProductRequest request, @MappingTarget Product product);
}
