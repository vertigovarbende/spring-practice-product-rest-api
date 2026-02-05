package com.deveyk.northwind.product.service.impl;

import com.deveyk.northwind.product.exception.CannotDeleteSupplierException;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.repository.SupplierRepository;
import com.deveyk.northwind.product.service.ISupplierService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService implements ISupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    @Override
    @Cacheable(value = "products", key = "#id")
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Supplier not found"));
    }

    @Override
    @Cacheable(
            value = "suppliers",
            key = "'page=' + #pageable.pageNumber + ':size=' + #pageable.pageSize + ':sort=companyName'"
    )
    public Page<Supplier> findAll(Pageable pageable) {
        return supplierRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(value = "suppliers", allEntries = true)
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(Supplier.builder()
                        .companyName(supplier.getCompanyName())
                        .contactName(supplier.getContactName())
                        .contactTitle(supplier.getContactTitle())
                        .address(supplier.getAddress())
                        .city(supplier.getCity())
                        .country(supplier.getCountry())
                        .phone(supplier.getPhone())
                        .build());
    }

    @Override
    @CacheEvict(value = "suppliers", allEntries = true)
    public Supplier update(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    @CacheEvict(value = "suppliers", allEntries = true)
    public void deleteById(Long id) {
        if (!supplierRepository.existsById(id)) {
            throw new EntityNotFoundException("Supplier not found");
        }
        long productCount = productRepository.countBySupplierId(id);
        if (productCount > 0) {
            throw new CannotDeleteSupplierException("Cannot delete supplier that has products. Product count: " + productCount);
        }
        supplierRepository.deleteById(id);
    }

    @Override
    public long getProductCountBySupplierId(Long supplierId) {
        if (!supplierRepository.existsById(supplierId)) {
            throw new EntityNotFoundException("Supplier not found");
        }
        return productRepository.countBySupplierId(supplierId);
    }

    @Override
    public Page<Supplier> findByCountry(String country, Pageable pageable) {
        return supplierRepository.findByCountry(country, pageable);
    }

    @Override
    public Page<Supplier> findPopularSuppliers(Pageable pageable) {
        return supplierRepository.findPopularSuppliers(pageable);
    }
}
