package com.deveyk.northwind.order.model.entity;

import com.deveyk.northwind.common.entity.BaseEntity;
import com.deveyk.northwind.order.model.enums.OrderStatus;
import com.deveyk.northwind.product.model.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
public class OrderDetails extends BaseEntity {

    @EmbeddedId
    private OrderDetailsId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "total", precision = 10, scale = 2, nullable = false)
    private BigDecimal total;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "discount", precision = 10, scale = 2, nullable = false)
    private BigDecimal discount;

}
