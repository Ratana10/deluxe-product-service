package com.deluxe.product_service.controllers;

import com.deluxe.product_service.dto.CategoryResponse;
import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import com.deluxe.product_service.services.ProductService;
import com.deluxe.product_service.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAll() {
        return this.productService.getAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("get success")
                .data(this.productService.getById(id))
                .path(request.getRequestURI())
                .build());
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(@RequestBody ProductRequest dto, HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("create success")
                .data(this.productService.create(dto))
                .path(request.getRequestURI())
                .build());
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(@PathVariable Long id, @RequestBody ProductRequest dto, HttpServletRequest request) {
        return ResponseEntity.ok(ApiResponse.<ProductResponse>builder()
                .success(true)
                .message("update success")
                .data(this.productService.update(id, dto))
                .path(request.getRequestURI())
                .build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id, HttpServletRequest request) {
        this.productService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .success(true)
                .message("delete success")
                .data(null)
                .path(request.getRequestURI())
                .build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<ProductResponse>> getByName(
            @PathVariable String name,
            HttpServletRequest request) {
        return ResponseEntity.ok(
                ApiResponse.<ProductResponse>builder()
                        .success(true)
                        .message("get successfully")
                        .data(productService.getByName(name))
                        .path(request.getRequestURI())
                        .build()
        );
    }
}
