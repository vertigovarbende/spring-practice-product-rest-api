package com.deveyk.northwind.order.repository;

import com.deveyk.northwind.order.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {

    Set<Order> findOrdersByCustomerId(Long customerId);

    Page<Order> findOrdersByCustomerId(Long id, Pageable pageable);

    Order findOrderByCustomerId(Long customerId);


}
