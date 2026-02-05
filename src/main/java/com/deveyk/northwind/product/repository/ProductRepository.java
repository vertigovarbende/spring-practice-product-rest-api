package com.deveyk.northwind.product.repository;

import com.deveyk.northwind.product.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long>, JpaRepository<Product, Long> {

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findByCategoryName(String categoryName, Pageable pageable);

    Page<Product> findBySupplierId(Long supplierId, Pageable pageable);

    Page<Product> findBySupplierCompanyName(String companyName, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.unitsInStock <= p.reorderLevel")
    Page<Product> findLowStock(Pageable pageable);

    Page<Product> findByDiscontinued(Boolean discontinued, Pageable pageable);

    long countByCategoryId(Long categoryId);

    long countBySupplierId(Long supplierId);

}
