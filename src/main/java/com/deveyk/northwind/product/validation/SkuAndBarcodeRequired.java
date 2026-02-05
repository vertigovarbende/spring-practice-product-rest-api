package com.deveyk.northwind.product.validation;


import com.deveyk.northwind.product.validation.validator.SkuAndBarcodeRequiredValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SkuAndBarcodeRequiredValidator.class)
public @interface SkuAndBarcodeRequired {

    String message() default "Both SKU and Barcode are required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
