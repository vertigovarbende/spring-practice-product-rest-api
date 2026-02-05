package com.deveyk.northwind.order.service;

import com.deveyk.northwind.order.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {

    Order findById(Long orderId);

    Page<Order> findAll(Pageable pageable);

    Order save(Order order);

    Order update(Order order);

    void delete(Long orderId);
}
