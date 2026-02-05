package com.deveyk.northwind.order.model.response;

import com.deveyk.northwind.order.model.enums.OrderStatus;
import com.deveyk.northwind.product.model.response.ProductResponse;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsResponse {

    private ProductResponse product;
    private BigDecimal unitPrice;
    private Integer quantity;
    private BigDecimal discount;
    private OrderStatus orderStatus;

}
