package com.deveyk.northwind.common.validation.validator;

import com.deveyk.northwind.common.validation.Phone;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class PhoneValidator implements ConstraintValidator<Phone, String> {

    private static final Pattern FORMATTED_PATTERN = Pattern.compile(
            "^\\+?[1-9]\\d{0,2}[\\s.-]?(\\(?\\d{2,4}\\)?[\\s.-]?){1,4}\\d{2,4}$"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true;
        }

        if (!FORMATTED_PATTERN.matcher(value).matches()) {
            return false;
        }
        String normalized = value.replaceAll("[^+\\d]", "");

        if (normalized.startsWith("+")) {
            normalized = normalized.substring(1);
        }

        return normalized.length() >= 10 && normalized.length() <= 15;
    }
}

