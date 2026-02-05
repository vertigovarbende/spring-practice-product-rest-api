package com.deveyk.northwind.product.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class InsufficientStockException extends BusinessException {

    public InsufficientStockException(String message) {
        super(message);
    }
}


