package com.deveyk.northwind.product.service;

import com.deveyk.northwind.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    Product findById(Long id);

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    Product update(Product product);

    void deleteById(Long id);

    Page<Product> findByCategoryId(Long categoryId, Pageable pageable);

    Page<Product> findByCategoryName(String categoryName, Pageable pageable);

    Page<Product> findBySupplierId(Long supplierId, Pageable pageable);

    Page<Product> findBySupplierCompanyName(String companyName, Pageable pageable);

    Page<Product> findLowStock(Pageable pageable);

    Product adjustStock(Long productId, Long quantityChange);

    Page<Product> findDiscontinued(Pageable pageable);

    Product discontinue(Long productId);

    Product continueProduct(Long productId);

}
