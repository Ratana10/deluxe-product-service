package com.deluxe.product_service.controllers;

import com.deluxe.product_service.services.CategoryService;
import com.deluxe.product_service.dto.CategoryRequest;
import com.deluxe.product_service.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return this.categoryService.getAll();
    }

    @GetMapping("{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return this.categoryService.getById(id);
    }

    @PostMapping
    public CategoryResponse create(@RequestBody CategoryRequest request) {

        return this.categoryService.create(request);
    }

    @PutMapping("{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest category) {
        return this.categoryService.update(id, category);
    }
}
