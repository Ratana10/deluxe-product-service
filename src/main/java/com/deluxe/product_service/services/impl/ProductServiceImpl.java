package com.deluxe.product_service.services.impl;

import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import com.deluxe.product_service.mapper.ProductMapper;
import com.deluxe.product_service.repositories.ProductRepository;
import com.deluxe.product_service.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;

    @Override
    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);
        return mapper.toResponse(repo.save(product));
    }

    @Override
    public ProductResponse getById(Long id) {
        return null;
    }

    @Override
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        getById(id);
        repo.deleteById(id);
    }
}
