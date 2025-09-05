package com.deluxe.product_service.mapper;

import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductRequest dto);
    ProductResponse toResponse(Product product);
}
