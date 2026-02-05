package com.deveyk.northwind.product.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class CannotDeleteCategoryException extends BusinessException {

    public CannotDeleteCategoryException(String message) {
        super(message);
    }
}

