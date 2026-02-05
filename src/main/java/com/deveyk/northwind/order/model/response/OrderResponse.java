package com.deveyk.northwind.order.model.response;

import com.deveyk.northwind.customer.model.response.CustomerResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long id;
    private CustomerResponse customer;
    private LocalDateTime orderDate;
    private LocalDateTime requiredDate;
    private LocalDateTime shippedDate;
    private Set<OrderDetailsResponse> orderDetails;



}
