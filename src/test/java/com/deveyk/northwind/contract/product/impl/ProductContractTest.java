package com.deveyk.northwind.contract.product.impl;

import com.deveyk.northwind.contract.product.ProductBaseContractTest;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.model.response.CategoryResponse;
import com.deveyk.northwind.product.model.response.ProductResponse;
import com.deveyk.northwind.product.model.response.SupplierResponse;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class ProductContractTest extends ProductBaseContractTest {

    private Product testProduct;
    private Category testCategory;
    private Supplier testSupplier;
    private ProductResponse testProductResponse;
    private CategoryResponse testCategoryResponse;
    private SupplierResponse testSupplierResponse;

    @Override
    protected void setupMockDataAndBehaviors() {
        // TEST DATA

        // Create Category
        testCategory = Category.builder()
                .id(1L)
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
                .build();

        // Create CategoryResponse
        testCategoryResponse = CategoryResponse.builder()
                .id(testCategory .getId())
                .name(testCategory .getName())
                .description(testCategory .getDescription())
                .build();

        // Create Supplier
        testSupplier = Supplier.builder()
                .id(1L)
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

        // Create SupplierResponse
        testSupplierResponse = SupplierResponse.builder()
                .id(testSupplier.getId())
                .companyName(testSupplier.getCompanyName())
                .contactName(testSupplier.getContactName())
                .contactTitle(testSupplier.getContactTitle())
                .address(testSupplier.getAddress())
                .country(testSupplier.getCountry())
                .phone(testSupplier.getPhone())
                .homepage(testSupplier.getHomepage())
                .build();

        // Create Product
        testProduct = Product.builder()
                .id(1000L)
                .name("Chai")
                .category(testCategory)
                .supplier(testSupplier)
                .sku("SP75017")
                .barcode("BAR1000000001")
                .quantityPerUnit("10 boxes x 20 bags")
                .price(BigDecimal.valueOf(18.00))
                .unitsInStock(39L)
                .unitsOnOrder(0L)
                .reorderLevel(10L)
                .discontinued(false)
                .build();

        // Create ProductResponse with correct structure
        testProductResponse = ProductResponse.builder()
                .id(testProduct.getId())
                .name(testProduct.getName())
                .category(testCategoryResponse)
                .supplier(testSupplierResponse)
                .sku(testProduct.getSku())
                .barcode(testProduct.getBarcode())
                .quantityPerUnit(testProduct.getQuantityPerUnit())
                .price(testProduct.getPrice())
                .unitsInStock(testProduct.getUnitsInStock())
                .unitsOnOrder(testProduct.getUnitsOnOrder())
                .build();

        // MOCK SERVICE BEHAVIORS

        // Mock for find product GET /api/products/1
        given(productService.findById(1000L)).willReturn(testProduct);
        given(productMapper.toResponse(testProduct)).willReturn(testProductResponse);

        // Mock for GET /api/products/9999 (it will throw EntityNotFoundException for proper 404 handling)
        given(productService.findById(9999L)).willThrow(new EntityNotFoundException("Product not found"));

        // Mock for GET /api/products ????
        given(productService.findAll(any(Pageable.class))).willReturn(new PageImpl<>(List.of(testProduct)));

        // Mock for create product POST /api/products
        given(productMapper.toEntity(any(ProductRequest.class))).willReturn(testProduct);
        given(productService.save(any(Product.class))).willReturn(testProduct);
        given(productMapper.toResponse(any(Product.class))).willReturn(testProductResponse);

        // Mock for update product PUT /api/products/1
        given(productService.update(any(Product.class))).willReturn(testProduct);

        // Mock for delete product DELETE /api/products/1 - no return value needed

    }

    @Test
    @DisplayName("Contract: GET /api/products/{id} should return product with correct structure")
    void contractGetProductByIdShouldReturnCorrectStructure() throws Exception {

        // max 10 entries !
        /*
        Map<String, Object> expectedResponse = Map.of(
                "id", 1000L,
                "name", "Chai",
                "category", Map.of(
                        "id", 1L,
                        "name", "Beverages",
                        "description", "Soft drinks, coffees, teas, beers, and ales"
                ),
                "supplier", Map.of(
                        "id", 1L,
                        "companyName", "Exotic Liquids",
                        "contactName", "Charlotte Cooper",
                        "contactTitle", "Purchasing Manager",
                        "address", "49 Gilbert St.",
                        "country", "UK",
                        "phone", "(171) 555-2222",
                        "homepage", "blablahblah"
                ),
                "sku", "SP75017",
                "barcode", "BAR1000000001",
                "quantityPerUnit", "10 boxes x 20 bags",
                "price", BigDecimal.valueOf(18.00),
                "unitInStock", 39L,
                "unitOnOrder", 0L,
                "_links", Map.of(
                        "self", Map.of(
                                "href", "http://localhost/api/products/1000",
                                "type", "GET"
                        ),
                        "products", Map.of(
                                "href", "http://localhost/api/products",
                                "type", "GET"
                        ),
                        "category", Map.of(
                                "href", "http://localhost/api/categories/1",
                                "type", "GET"
                        ),
                        "supplier", Map.of(
                                "href", "http://localhost/api/supplier/1",
                                "type", "GET"
                        )
                )
        );
         */

        // Given - Setup Provider contract (what API promises to return)

        Map<String, Object> expectedResponse = Map.ofEntries(
                Map.entry("id", 1000L),
                Map.entry("name", "Chai"),
                Map.entry("category", Map.of(
                        "id", 1L,
                        "name", "Beverages",
                        "description", "Soft drinks, coffees, teas, beers, and ales"
                )),
                Map.entry("supplier", Map.of(
                        "id", 1L,
                        "companyName", "Exotic Liquids",
                        "contactName", "Charlotte Cooper",
                        "contactTitle", "Purchasing Manager",
                        "address", "49 Gilbert St.",
                        "country", "UK",
                        "phone", "(171) 555-2222",
                        "homepage", "blablahblah"
                )),
                Map.entry("sku", "SP75017"),
                Map.entry("barcode", "BAR1000000001"),
                Map.entry("quantityPerUnit", "10 boxes x 20 bags"),
                Map.entry("price", BigDecimal.valueOf(18.00)),
                Map.entry("unitInStock", 39L),
                Map.entry("unitOnOrder", 0L),
                Map.entry("_links", Map.of(
                        "self", Map.of(
                                "href", "http://localhost/api/products/1000",
                                "type", "GET"
                        ),
                        "products", Map.of(
                                "href", "http://localhost/api/products",
                                "type", "GET"
                        ),
                        "category", Map.of(
                                "href", "http://localhost/api/categories/1",
                                "type", "GET"
                        ),
                        "supplier", Map.of(
                                "href", "http://localhost/api/supplier/1",
                                "type", "GET"
                        )
                ))
        );

        // refactor stubForRequest method in BaseContractTest
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/1000"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponse))));


        // When - Consumer makes create request
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/1000", HttpMethod.GET);

        // Then - Verify contract
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);

        // Contract validation
        assertEquals(1000, responseBody.get("id"));
        assertEquals("Chai", responseBody.get("name"));
        assertEquals(18.00, responseBody.get("price"));
        assertTrue(responseBody.containsKey("_links"));

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/1000"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

}
