package com.deluxe.product_service.services.impl;

import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import com.deluxe.product_service.mapper.ProductMapper;
import com.deluxe.product_service.repositories.ProductRepository;
import com.deluxe.product_service.services.CategoryService;
import com.deluxe.product_service.services.ProductService;
import com.deluxe.product_service.utils.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;
    private final ProductMapper mapper;
    private final CategoryService categoryService;

    @Override
    @CacheEvict(value = "product:list", key = "'all'")
    public ProductResponse create(ProductRequest dto) {
        Product product = mapper.toEntity(dto);
        return mapper.toResponse(repo.save(product));
    }

    @Override
    @Cacheable(value = "product:byId", key = "#id")
    public ProductResponse getById(Long id) {
        categoryService.getById(id);
        return repo.findById(id)
                .map(mapper::toResponse)
                .orElseThrow(()-> new NotFoundException("Product not found"));
    }

    @Override
    @Cacheable(value = "product:byName", key = "#name")
    public ProductResponse getByName(String name) {
        return repo.findByName(name)
                .map(mapper::toResponse)
                .orElseThrow(()-> new NotFoundException("Product not found"));
    }

    @Override
    @Cacheable(value = "product:list", key = "'all'")
    public List<Product> getAll() {
        return repo.findAll();
    }

    @Override
    @CacheEvict(value = "product:list", key = "'all'")
    @CachePut(value = "product:byId", key = "#id")
    public ProductResponse update(Long id, ProductRequest request) {
        return null;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "product:byId", key = "#id"),
            @CacheEvict(value = "product:list", key = "'all'")
    })
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
