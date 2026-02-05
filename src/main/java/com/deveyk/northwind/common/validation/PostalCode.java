package com.deveyk.northwind.common.validation;

import com.deveyk.northwind.common.validation.validator.PhoneValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = PhoneValidator.class)
@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface PostalCode {

    String message() default "Invalid postal code";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}