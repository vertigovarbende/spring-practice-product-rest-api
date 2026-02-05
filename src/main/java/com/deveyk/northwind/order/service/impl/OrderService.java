package com.deveyk.northwind.order.service.impl;

import com.deveyk.northwind.order.model.entity.Order;
import com.deveyk.northwind.order.repository.OrderRepository;
import com.deveyk.northwind.order.service.IOrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
    }

    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return null;
    }

    @Override
    public void delete(Long orderId) {
        if (orderRepository.findById(orderId).isEmpty()) {
            throw new EntityNotFoundException("Order not found");
        }
        orderRepository.deleteById(orderId);
    }
}
