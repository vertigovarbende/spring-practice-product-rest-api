package com.deveyk.northwind.customer.service.impl;

import com.deveyk.northwind.customer.exception.OrderNotFoundException;
import com.deveyk.northwind.customer.model.entity.Customer;
import com.deveyk.northwind.customer.repository.CustomerRepository;
import com.deveyk.northwind.customer.service.ICustomerService;
import com.deveyk.northwind.order.model.entity.Order;
import com.deveyk.northwind.order.model.enums.OrderStatus;
import com.deveyk.northwind.order.repository.OrderRepository;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    /**
     * Retrieves customer information based on the provided authentication details.
     *
     * @param authentication the authentication object containing user credentials
     * @return the customer associated with the authenticated user
     * @throws EntityNotFoundException if the customer cannot be found
     */
    @Override
    public Customer getInfo(Authentication authentication) {
        return customerRepository.findCustomerByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    /**
     * Finds and retrieves a customer based on the provided ID.
     * The result is cached to improve performance on subsequent calls.
     *
     * @param id the unique identifier of the customer to find
     * @return the customer entity associated with the given ID
     * @throws EntityNotFoundException if no customer is found with the provided ID
     */
    @Override
    @Cacheable(value = "customers", key = "#id")
    public Customer findById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Override
    @Cacheable(
            value = "customers",
            key = "'page=' + #pageable.pageNumber + ':size=' + #pageable.pageSize + ':sort=companyName'"
    )
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Order getOrder(Long id, Authentication authentication) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("You don't have order with the id " + id));

        if (authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN") ||
                        authority.getAuthority().equals("ROLE_MANAGER"))) {
            return order;
        }

        Customer customer = customerRepository.findCustomerByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Set<Order> customerOrders = orderRepository.findOrdersByCustomerId(customer.getId());

        if (customerOrders.contains(order)) {
            return order;
        } else {
            throw new OrderNotFoundException("You don't have order with the id " + id);
        }
    }

    // others roles like admin, manager cannot access this endpoint
    @Override
    public Page<Order> getAllOrders(Pageable pageable, Authentication authentication) {
        Customer customer = customerRepository.findCustomerByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
        return orderRepository.findOrdersByCustomerId(customer.getId(), pageable);
    }


    @Override
    public Order cancelOrder(Long id, Authentication authentication) {
        Customer customer = customerRepository.findCustomerByUsername(authentication.getName())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));

        Set<Order> customerOrders = orderRepository.findOrdersByCustomerId(customer.getId());

        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("You don't have order with the id " + id));
        order.setOrderStatus(OrderStatus.CANCELLED);
        return order;
    }
}
