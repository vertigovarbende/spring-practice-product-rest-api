package com.deveyk.northwind.product.validation.validator;

import com.deveyk.northwind.product.validation.BAR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class BARValidator implements ConstraintValidator<BAR, String> {

    private static final Pattern BAR_PATTERN = Pattern.compile("^BAR\\d{10}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null)
            return true;
        return BAR_PATTERN.matcher(value).matches();
    }
}
