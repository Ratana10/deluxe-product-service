package com.deluxe.product_service.services;
import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    ProductResponse getByName(String name);
    List<ProductResponse> getAll();
    ProductResponse update(Long id, ProductRequest request);
    void deleteById(Long id);
}
