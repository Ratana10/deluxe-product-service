package com.deluxe.product_service.controllers;

import com.deluxe.product_service.entities.Category;
import com.deluxe.product_service.services.CategoryService;
import com.deluxe.product_service.dto.CategoryRequest;
import com.deluxe.product_service.dto.CategoryResponse;
import com.deluxe.product_service.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

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
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.<CategoryResponse>builder()
                .success(true)
                .message("get success")
                .data(this.categoryService.getById(id))
                .path(request.getRequestURI())
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>>  create(@RequestBody CategoryRequest dto, HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.<CategoryResponse>builder()
                .success(true)
                .message("create success")
                .data(this.categoryService.create(dto))
                .path(request.getRequestURI())
                .build());
    }

    @PutMapping("{id}")
    public CategoryResponse update(@PathVariable Long id, @RequestBody CategoryRequest category) {
        return this.categoryService.update(id, category);
    }
}
