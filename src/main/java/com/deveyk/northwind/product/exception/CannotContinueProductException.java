package com.deveyk.northwind.product.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class CannotContinueProductException extends BusinessException {

    public CannotContinueProductException(String message) {
        super(message);
    }
}
