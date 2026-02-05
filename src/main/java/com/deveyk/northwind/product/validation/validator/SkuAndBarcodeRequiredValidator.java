package com.deveyk.northwind.product.validation.validator;

import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.validation.SkuAndBarcodeRequired;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SkuAndBarcodeRequiredValidator implements ConstraintValidator<SkuAndBarcodeRequired, ProductRequest> {

    @Override
    public boolean isValid(ProductRequest productRequest, ConstraintValidatorContext constraintValidatorContext) {
        if (productRequest == null)
            return true;
        return (productRequest.getSku() != null && !productRequest.getSku().isBlank()) ||
                (productRequest.getBarcode() != null && !productRequest.getBarcode().isBlank());
    }
}
