package com.deveyk.northwind.product.model.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private CategoryResponse category;
    private SupplierResponse supplier;
    private String sku;
    private String barcode;
    private String quantityPerUnit;
    private BigDecimal price;
    private Long unitsInStock;
    private Long unitsOnOrder;


}
