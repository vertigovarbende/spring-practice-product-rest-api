package com.deveyk.northwind.customer.service;

import com.deveyk.northwind.customer.model.entity.Customer;
import com.deveyk.northwind.order.model.entity.Order;
import com.deveyk.northwind.product.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

public interface ICustomerService {

    Customer getInfo(Authentication authentication);

    Customer findById(Long id);

    Page<Customer> findAll(Pageable pageable);

    Product getProduct(Long id);

    Page<Product> getAllProducts(Pageable pageable);

    Order getOrder(Long id, Authentication authentication);

    Page<Order> getAllOrders(Pageable pageable, Authentication authentication);

    Order cancelOrder(Long id, Authentication authentication);

}
