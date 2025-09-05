package com.deluxe.product_service.controllers;

import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import com.deluxe.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
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
    public ProductResponse getById(@PathVariable Long id) {
        return this.productService.getById(id);
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductRequest request) {
        return this.productService.create(request);
    }

    @PutMapping("{id}")
    public ProductResponse update(@PathVariable Long id, @RequestBody ProductRequest Product) {
        return this.productService.update(id, Product);
    }
}
