package com.deveyk.northwind.product.repository;

import com.deveyk.northwind.product.model.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long>, JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    @Query("SELECT c FROM Category c ORDER BY (SELECT COUNT(p) FROM Product p WHERE p.category.id = c.id) DESC")
    Page<Category> findPopularCategories(Pageable pageable);
}
