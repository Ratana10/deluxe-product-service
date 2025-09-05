package com.deluxe.product_service.services.impl;

import com.deluxe.product_service.dto.ProductColorRequest;
import com.deluxe.product_service.dto.ProductRequest;
import com.deluxe.product_service.dto.ProductResponse;
import com.deluxe.product_service.entities.Product;
import com.deluxe.product_service.entities.ProductColor;
import com.deluxe.product_service.mapper.ProductColorMapper;
import com.deluxe.product_service.mapper.ProductMapper;
import com.deluxe.product_service.repositories.ProductRepository;
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
    private final ProductColorMapper colorMapper;

    @Override
    @CacheEvict(value = "product:list", key = "'all'")
    public ProductResponse create(ProductRequest request) {
        Product product = mapper.toEntity(request);

        if (request.getProductColors() != null) {
            for (ProductColorRequest colorRequest : request.getProductColors()) {
                ProductColor productColor = colorMapper.toEntity(colorRequest);
                product.addProductColor(productColor);
            }
        }
        return mapper.toResponse(repo.save(product));
    }

    @Override
    @Cacheable(value = "product:byId", key = "#id")
    public ProductResponse getById(Long id) {
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
    public List<ProductResponse> getAll() {
        List<Product> products = repo.findAllWithColors();
        return products.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    @CacheEvict(value = "product:list", key = "'all'")
    @CachePut(value = "product:byId", key = "#id")
    public ProductResponse update(Long id, ProductRequest dto) {
        Product existing = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with id: " + id));

        mapper.updateProductFromRequest(dto, existing);
        if (dto.getProductColors() != null) {
            existing.getProductColors().clear();
            for (ProductColorRequest colorRequest : dto.getProductColors()) {
                ProductColor productColor = colorMapper.toEntity(colorRequest);
                existing.addProductColor(productColor);
            }
        } else {
            existing.getProductColors().clear();
        }
        return mapper.toResponse(repo.save(existing));
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
