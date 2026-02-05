package com.deveyk.northwind.product.service;


import com.deveyk.northwind.product.model.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ISupplierService {

    Supplier findById(Long id);

    Page<Supplier> findAll(Pageable pageable);

    Supplier save(Supplier supplier);

    Supplier update(Supplier supplier);

    void deleteById(Long id);

    long getProductCountBySupplierId(Long supplierId);

    Page<Supplier> findByCountry(String country, Pageable pageable);

    Page<Supplier> findPopularSuppliers(Pageable pageable);
}
