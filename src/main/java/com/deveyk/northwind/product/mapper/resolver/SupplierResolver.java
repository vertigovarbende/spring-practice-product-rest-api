package com.deveyk.northwind.product.mapper.resolver;

import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.SupplierRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplierResolver {

    private final SupplierRepository supplierRepository;

    @Named("resolveSupplier")
    public Supplier resolve(String supplierName) {
        return supplierRepository.findByCompanyName(supplierName)
                .orElseThrow(() ->
                        new EntityNotFoundException("Supplier not found: " + supplierName));
    }
}
