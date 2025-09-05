package com.deluxe.product_service.mapper;

import com.deluxe.product_service.dto.ProductColorRequest;
import com.deluxe.product_service.dto.ProductColorResponse;
import com.deluxe.product_service.entities.ProductColor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductColorMapper {
    ProductColor toEntity(ProductColorRequest dto);

    ProductColorResponse toResponse(ProductColorRequest dto);
}
