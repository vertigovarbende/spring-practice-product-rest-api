package com.deveyk.northwind.product.repository;

import com.deveyk.northwind.product.model.entity.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends PagingAndSortingRepository<Supplier, Long>, JpaRepository<Supplier, Long> {

    Optional<Supplier> findByCompanyName(String companyName);

    Page<Supplier> findByCountry(String country, Pageable pageable);

    @Query("SELECT s FROM Supplier s ORDER BY (SELECT COUNT(p) FROM Product p WHERE p.supplier.id = s.id) DESC")
    Page<Supplier> findPopularSuppliers(Pageable pageable);

}
