package com.deveyk.northwind.product.model.request;

import com.deveyk.northwind.product.validation.BAR;
import com.deveyk.northwind.product.validation.SP;
import com.deveyk.northwind.product.validation.SkuAndBarcodeRequired;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SkuAndBarcodeRequired(message = "Sku and barcode are required")
public class ProductRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 40, message = "Product name is too long")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Supplier is required")
    private String supplier;

    @SP
    private String sku;

    @BAR
    private String barcode;

    @NotNull(message = "Price is required")
    @PositiveOrZero(message = "Price must be non-negative")
    private Double price;

    @NotBlank(message = "Quantity per unit is required")
    @Size(max = 20, message = "Quantity per unit is too long")
    private String quantityPerUnit;

    @NotNull(message = "Units in stock is required")
    @PositiveOrZero(message = "Units in stock must be non-negative")
    private Long unitsInStock;


}
