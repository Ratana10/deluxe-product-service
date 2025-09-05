package com.deluxe.product_service.services.impl;

import com.deluxe.product_service.entities.Category;
import com.deluxe.product_service.mapper.CategoryMapper;
import com.deluxe.product_service.repositories.CategoryRepository;
import com.deluxe.product_service.dto.CategoryRequest;
import com.deluxe.product_service.dto.CategoryResponse;
import com.deluxe.product_service.services.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import io.micrometer.observation.annotation.Observed;
import static net.logstash.logback.argument.StructuredArguments.kv;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repo;
    private final CategoryMapper mapper;

    @Override
    @Observed(name = "categories.getById", lowCardinalityKeyValues = {"operation", "getById"})
    public CategoryResponse getById(Long id) {
        log.info("Fetching category {}", kv("categoryId", id));
        var entity = repo.findById(id).orElse(null);
        if (entity == null) {
            log.warn("Category not found {}", kv("categoryId", id));
            return null; // or throw ResourceNotFoundException
        }
        var resp = mapper.toResponse(entity);
        log.debug("Fetched category {}", kv("categoryId", id));
        return resp;
    }

    @Override
    @Observed(name = "categories.getAll", lowCardinalityKeyValues = {"operation", "getAll"})
    public List<Category> getAll() {
        log.info("Listing categories");
        var list = repo.findAll();
        log.debug("Listed categories {}", kv("count", list.size()));
        return list;
    }

    @Override
    @Observed(name = "categories.update", lowCardinalityKeyValues = {"operation", "update"})
    public CategoryResponse update(Long id, CategoryRequest request) {
        log.info("Updating category {}", kv("categoryId", id));
        var entity = repo.findById(id).orElse(null);
        if (entity == null) {
            log.warn("Cannot update; category not found {}", kv("categoryId", id));
            return null; // or throw
        }
        // partial update using mapper (ignore nulls)
//        Category entity = mapper.toEntity(request);
        var saved = repo.save(entity);
        log.info("Updated category {}", kv("categoryId", saved.getId()));
        return mapper.toResponse(saved);
    }

    @Override
    @Observed(name = "categories.delete", lowCardinalityKeyValues = {"operation", "delete"})
    public void deleteById(Long id) {
        log.info("Deleting category {}", kv("categoryId", id));
        if (!repo.existsById(id)) {
            log.warn("Delete skipped; category not found {}", kv("categoryId", id));
            return; // or throw
        }
        repo.deleteById(id);
        log.info("Deleted category {}", kv("categoryId", id));
    }

    @Override
    @Observed(name = "categories.create", lowCardinalityKeyValues = {"operation", "create"})
    public CategoryResponse create(CategoryRequest request) {
        log.info("Creating category {}", kv("name", request.getName()));
        var entity = mapper.toEntity(request);
        var saved = repo.save(entity);
        log.info("Created category {}", kv("categoryId", saved.getId()));
        return mapper.toResponse(saved);
    }
}
