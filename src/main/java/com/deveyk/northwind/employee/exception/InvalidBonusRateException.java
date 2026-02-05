package com.deveyk.northwind.employee.exception;

import com.deveyk.northwind.common.exception.BusinessException;

public class InvalidBonusRateException extends BusinessException {

    public InvalidBonusRateException(String message) {
        super(message);
    }
}
