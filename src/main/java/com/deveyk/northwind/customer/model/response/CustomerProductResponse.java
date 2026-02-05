package com.deveyk.northwind.customer.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProductResponse {

    private String name;
    private CustomerCategoryResponse category;
    private BigDecimal price;
    private Long quantity;

}
