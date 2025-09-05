package com.deluxe.product_service.services;

import com.deluxe.product_service.entities.Category;
import com.deluxe.product_service.dto.CategoryRequest;
import com.deluxe.product_service.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest category);
    CategoryResponse getById(Long id);
    List<Category> getAll();
    CategoryResponse update(Long id, CategoryRequest category);
    void deleteById(Long id);
}
