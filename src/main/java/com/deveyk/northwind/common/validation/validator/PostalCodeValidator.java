package com.deveyk.northwind.common.validation.validator;

import com.deveyk.northwind.common.validation.PostalCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PostalCodeValidator implements ConstraintValidator<PostalCode, String> {

    private static final Pattern FORMATTED_PATTERN = Pattern.compile(
            "^[0-9]{4,10}$"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       return FORMATTED_PATTERN.matcher(value).matches();
    }

}
