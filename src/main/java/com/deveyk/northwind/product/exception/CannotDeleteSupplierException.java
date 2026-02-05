package com.deveyk.northwind.product.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class CannotDeleteSupplierException extends BusinessException {

    public CannotDeleteSupplierException(String message) {
        super(message);
    }
}

