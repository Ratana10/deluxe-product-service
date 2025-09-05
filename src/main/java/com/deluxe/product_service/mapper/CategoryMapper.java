package com.deluxe.product_service.mapper;

import com.deluxe.product_service.dto.CategoryRequest;
import com.deluxe.product_service.dto.CategoryResponse;
import com.deluxe.product_service.controllers.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest dto);
    CategoryResponse toResponse(Category customer);
}
