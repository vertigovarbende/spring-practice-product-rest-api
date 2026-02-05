package com.deveyk.northwind.product.service;

import com.deveyk.northwind.product.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICategoryService {

    Category findById(Long id);

    Page<Category> findAll(Pageable pageable);

    Category save(Category category);

    Category update(Category category);

    void deleteById(Long id);

    long getProductCountByCategoryId(Long categoryId);

    Page<Category> findPopularCategories(Pageable pageable);

}
