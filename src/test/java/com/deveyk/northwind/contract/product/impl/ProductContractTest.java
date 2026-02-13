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
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.runners.model.MultipleFailureException.assertEmpty;
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


        // When - Consumer makes get request
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

    @Test
    @DisplayName("Contract: GET /api/products/{id} should return 404 for non-existent resource")
    void contractGetProductByIdShouldReturn404ForNonExistentResource() throws Exception {
        // Given
        // - Contract specifies 404 for non-existing resources
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/9999"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        // - Consumer request non-existing resource
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/9999", HttpMethod.GET);

        // Then
        // - Contract should get 404 status code
        assertEquals(404, response.statusCode());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/9999")));
    }

    // GET - /api/products

    @Test
    @DisplayName("Contract: POST /api/products should accept correct payload and return created resource")
    void contractPostProductShouldAcceptCorrectPayloadAndReturnCreatedResource() throws Exception {

        // Given - Consumer's contract expectations
        Map<String, Object> payloadOfRequest = Map.of(
                "name",  "Test product",
                "category", "Beverages",
                "supplier", "Exotic Liquids",
                "sku", "SP00001",
                "barcode", "BAR1000000001",
                "price", 149.99,
                "quantityPerUnit", "quantity-per-unit",
                "unitInStock", 15
        );

        Map<String, Object> expectedResponse = Map.ofEntries(
                Map.entry("id", 1000L),
                Map.entry("name", "Test Product"),
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
                Map.entry("price", BigDecimal.valueOf(149.99)),
                Map.entry("unitInStock", 15L),
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
        stubFor(post(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .withRequestBody(equalToJson(toJson(payloadOfRequest)))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.CREATED.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withHeader("Location", "http://localhost" + PRODUCT_ENDPOINT + "/1000")
                        .withBody(toJson(expectedResponse))));

        // When
        // - Consumer makes create request
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT, HttpMethod.POST, payloadOfRequest);

        // Then
        // - Verify contract compliance
        assertEquals(201, response.statusCode()); // CREATED
        assertThat(response.headers().firstValue("Location").orElse(""))
                .contains("http://localhost" + PRODUCT_ENDPOINT + "/1000");
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertEquals(1000, responseBody.get("id"));
        assertEquals("Test Product", responseBody.get("name"));
        assertEquals(149.99, responseBody.get("price"));
        assertTrue(responseBody.containsKey("_links"));

        // - Verify contract interaction
        verify(postRequestedFor(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: POST /api/products should return 404 for non-existent category resource")
    void contractPostProductShouldReturn404ForNonExistentCategoryResource() throws Exception {

        // Given
        // - Consumer's contract expectations
        //      - category has been changed
        Map<String, Object> payloadOfRequest = Map.of(
                "name", "Test product",
                "category", "NonExistentCategoryResource",
                "supplier", "Exotic Liquids",
                "sku", "SP00001",
                "barcode", "BAR1000000001",
                "price", 149.99,
                "quantityPerUnit", "quantity-per-unit",
                "unitInStock", 15
        );

        // Given
        // - Contract specifies 404 for non-existing resources
        stubFor(post(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .withRequestBody(equalToJson(toJson(payloadOfRequest)))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        // - Consumer requests non-existing category resource
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT, HttpMethod.POST, payloadOfRequest);

        // Then
        // - Contract should get 404 status codes
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(postRequestedFor(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json")));
    }

    @Test
    @DisplayName("Contract: POST /api/products should return 404 for non-existent supplier resource")
    void contractPostProductShouldReturn404ForNonExistentSupplierResource() throws Exception {


    }

    @Test
    @DisplayName("Contract: DELETE /api/products/{id} should delete product and return 204 No Content")
    void contractDeleteProductShouldDeleteProductAndReturn204NoContent() throws Exception {

        // Given
        // - Contract for delete operation
        stubFor(delete(urlEqualTo(PRODUCT_ENDPOINT + "/1000"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NO_CONTENT.value())));

        // When
        // - Consumer makes delete request

        // refactor sendRequest methods in BaseContractTest!
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + wireMockServer.port() + PRODUCT_ENDPOINT + "/1000"))
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Then
        assertEquals(204, response.statusCode());
        assertEquals("", response.body());

        verify(deleteRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/1000")));

    }

    @Test
    @DisplayName("Contract: PUT /api/products/{id} should accept correct payload and return updated resource")
    void contractPutProductShouldAcceptCorrectPayloadAndReturnUpdatedResource() throws Exception {

        // Given
        Map<String, Object> payloadOfRequest = Map.of(
                "name", "New Updated Product",
                "category", "Beverages",
                "supplier", "Exotic Liquids",
                "sku", "SP00001",
                "barcode", "BAR1000000001",
                "price", 189.99,
                "quantityPerUnit", "quantity-per-unit",
                "unitInStock", 10
        );

        Map<String, Object> expectedResponse = Map.ofEntries(
                Map.entry("id", 1000L),
                Map.entry("name", "New Updated Product"),
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
                Map.entry("price", BigDecimal.valueOf(189.99)),
                Map.entry("unitInStock", 10L),
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

        stubFor(put(urlEqualTo(PRODUCT_ENDPOINT + "/1000"))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .withRequestBody(equalToJson(toJson(payloadOfRequest)))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponse))));

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/1000", HttpMethod.PUT, payloadOfRequest);

        // Then
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertEquals(1000, responseBody.get("id"));
        assertEquals("New Updated Product", responseBody.get("name"));
        assertEquals(189.99, responseBody.get("price"));
        assertEquals(10, responseBody.get("unitInStock"));
        assertTrue(responseBody.containsKey("_links"));

        verify(putRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/1000"))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json")));
    }

}
