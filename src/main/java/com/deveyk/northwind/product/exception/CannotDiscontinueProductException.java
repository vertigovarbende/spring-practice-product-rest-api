package com.deveyk.northwind.product.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class CannotDiscontinueProductException extends BusinessException {

    public CannotDiscontinueProductException(String message) {
        super(message);
    }
}

