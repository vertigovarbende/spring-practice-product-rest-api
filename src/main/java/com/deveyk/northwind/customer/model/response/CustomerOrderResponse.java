package com.deveyk.northwind.customer.model.response;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerOrderResponse {

    private Long id;
    private String orderStatus;
    private BigDecimal total;
    private BigDecimal discount;
    private Set<CustomerProductResponse> products;
    private LocalDate orderDate;
    private LocalDate requiredDate;
    private LocalDate shippedDate;


}
