package com.deluxe.product_service.services;
import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Category;
import com.deluxe.product_service.entities.Product;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    List<Product> getAll();
    ProductResponse update(Long id, ProductRequest request);
    void deleteById(Long id);
}
