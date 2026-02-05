package com.deveyk.northwind.customer.repository;

import com.deveyk.northwind.customer.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>, JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByUsername(String username);

}
