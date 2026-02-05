package com.deveyk.northwind.product.validation;

import com.deveyk.northwind.product.validation.validator.BARValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BARValidator.class)
public @interface BAR {

    String message() default "Barcode must begin with 'BAR'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
