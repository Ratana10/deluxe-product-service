package com.deluxe.product_service.services.impl;

import com.deluxe.product_service.controllers.Category;
import com.deluxe.product_service.mapper.CategoryMapper;
import com.deluxe.product_service.repositories.CategoryRepository;
import com.deluxe.product_service.dto.CategoryRequest;
import com.deluxe.product_service.dto.CategoryResponse;
import com.deluxe.product_service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;
    private final CategoryMapper mapper;


    @Override
    public CategoryResponse getById(Long id) {
        Category category = repo.findById(id).orElse(null);
        return mapper.toResponse(category);
    }

    @Override
    public List<Category> getAll() {
        return repo.findAll();
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        getById(id);
        Category category = mapper.toEntity(request);
        return mapper.toResponse(repo.save(category));
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        log.info("Category create: {}", category);
        return mapper.toResponse(repo.save(category));
    }
}
