package com.deveyk.northwind.product.service.impl;

import com.deveyk.northwind.product.exception.CannotDeleteCategoryException;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.repository.CategoryRepository;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.service.ICategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = "categories", key = "#id")
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found"));
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public Category save(Category category) {
        return categoryRepository.save(Category.builder()
                .name(category.getName())
                .build());
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @CacheEvict(value = "categories", allEntries = true)
    public void deleteById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Category not found");
        }

        long productCount = productRepository.countByCategoryId(id);
        if (productCount > 0) {
            throw new CannotDeleteCategoryException("Cannot delete category that has products. Product count: " + productCount);
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public long getProductCountByCategoryId(Long categoryId) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        return productRepository.countByCategoryId(categoryId);
    }

    @Override
    public Page<Category> findPopularCategories(Pageable pageable) {
        return categoryRepository.findPopularCategories(pageable);
    }
}
