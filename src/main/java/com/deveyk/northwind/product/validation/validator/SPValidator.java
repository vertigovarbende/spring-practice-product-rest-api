package com.deveyk.northwind.product.validation.validator;

import com.deveyk.northwind.product.validation.SP;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;


public class SPValidator implements ConstraintValidator<SP, String> {

    private static final Pattern SP_PATTERN = Pattern.compile("^SP\\d{5}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null)
            return true;
        return SP_PATTERN.matcher(value).matches();
    }

}
