package com.deveyk.northwind.order.model.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class OrderDetailsId implements Serializable {

    private Long orderId;
    private Long productId;
}
