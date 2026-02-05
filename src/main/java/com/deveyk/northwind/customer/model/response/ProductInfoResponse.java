package com.deveyk.northwind.customer.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfoResponse {

    private Long id;
    private String name;
    private CustomerCategoryResponse category;
    private BigDecimal price;
    private String quantityPerUnit;
    private Long reorderLevel;
    private boolean discontinued;

}
