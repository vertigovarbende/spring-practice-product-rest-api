package com.deveyk.northwind.customer.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
