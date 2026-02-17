package com.deveyk.northwind.integration.product;

import lombok.experimental.UtilityClass;
import com.deveyk.northwind.product.model.entity.*;
import java.math.BigDecimal;

@UtilityClass
public class TestDataBuilder {

    public static Product createTestProduct() {
        Product product = Product.builder()
                .name("Test Product")
                .supplier(createTestSupplier())
                .category(createTestCategory())
                .sku("SP00001")
                .barcode("BAR1000000001")
                .quantityPerUnit("10 boxes x 20 bags")
                .price(BigDecimal.valueOf(18.00))
                .unitsInStock(39L)
                .unitsOnOrder(0L)
                .reorderLevel(10L)
                .discontinued(false)
                .build();
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());
        return product;
    }

    public static Product createTestProduct(String name, String sku, String barcode) {
        Product product = Product.builder()
                .name(name)
                .supplier(createTestSupplier())
                .category(createTestCategory())
                .sku(sku)
                .barcode(barcode)
                .quantityPerUnit("10 boxes x 20 bags")
                .price(BigDecimal.valueOf(18.00))
                .unitsInStock(39L)
                .unitsOnOrder(0L)
                .reorderLevel(10L)
                .discontinued(false)
                .build();
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());
        return product;
    }

    public static Category createTestCategory() {
        Category category = Category.builder()
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
                .build();
        category.setCreatedAt(java.time.LocalDateTime.now());
        category.setUpdatedAt(java.time.LocalDateTime.now());
        return category;
    }

    public static Supplier createTestSupplier() {
        Supplier supplier = Supplier.builder()
                .companyName("Exotic Liquids")
                .contactName("Charlotte Cooper")
                .contactTitle("Purchasing Manager")
                .address("49 Gilbert St.")
                .city("London")
                .region(null)
                .postalCode("EC1 4SD")
                .country("UK")
                .phone("(171) 555-2222")
                .fax(null)
                .homepage(null)
                .build();
        supplier.setCreatedAt(java.time.LocalDateTime.now());
        supplier.setUpdatedAt(java.time.LocalDateTime.now());
        return supplier;
    }

}
