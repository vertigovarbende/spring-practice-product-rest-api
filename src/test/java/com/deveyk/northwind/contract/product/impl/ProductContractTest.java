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


        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/1000"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponse))));


        // When - Consumer makes get request
        // refactor stubForRequest method in BaseContractTest
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
    @DisplayName("Contract: GET /api/products should return product list with correct structure")
    void contractGetProductListShouldReturnCorrectStructure() throws Exception {

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

        Map<String, Object> expectedResponseList = Map.of(
          "_embedded", Map.of(
                  "productList", List.of(
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse,
                          expectedResponse
                        ),
                        "_links", Map.of(
                                "self", Map.of(
                                        "href", "http://localhost/api/products",
                                        "type", "GET"
                                )
                        )
                )
        );

        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponseList))));

        // When - Consumer makes get request
        // refactor stubForRequest method in BaseContractTest
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT, HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertTrue(responseBody.containsKey("_embedded"));

        Map<String, Object> embedded = (Map<String, Object>) responseBody.get("_embedded");

        // Contract validation
        assertTrue(embedded.containsKey("productList"));
        assertTrue(embedded.containsKey("_links"));

        List<Map<String, Object>> productList = (List<Map<String, Object>>) embedded.get("productList");

        // Contract validation
        assertEquals(10, productList.size());
        assertEquals(1000, productList.get(0).get("id"));
        assertEquals("Chai", productList.get(0).get("name"));
        assertEquals(18.00, productList.get(0).get("price"));
        assertTrue(productList.get(0).containsKey("_links"));

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

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

        // Given
        // - Consumer's contract expectations
        Map<String, Object> payloadOfRequest = Map.of(
                "name", "Test product",
                "category", "Beverages",
                "supplier", "NonExistentSupplierResource",
                "sku", "SP00001",
                "barcode", "BAR1000000001",
                "price", 149.99,
                "quantityPerUnit", "quantity-per-unit",
                "unitInStock", 15
        );

        // Given
        // - Contract specifies 404 for non-existing supplier
        stubFor(post(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .withRequestBody(equalToJson(toJson(payloadOfRequest)))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        // - Consumer requests with non-existing supplier
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT, HttpMethod.POST, payloadOfRequest);

        // Then
        // - Contract should get 404 status code
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(postRequestedFor(urlEqualTo(PRODUCT_ENDPOINT))
                .withHeader("Content-Type", equalTo("application/hal+json"))
                .withHeader("Accept", equalTo("application/hal+json")));
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
    @DisplayName("Contract: DELETE /api/products/{id} should return 404 for non-existent product resource")
    void contractDeleteProductShouldReturn404ForNonExistentProductResource() throws Exception {

        // Given
        // - Contract for delete operation
        stubFor(delete(urlEqualTo(PRODUCT_ENDPOINT + "/9999"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        // - Consumer makes delete request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + wireMockServer.port() + PRODUCT_ENDPOINT + "/9999"))
                .header("Accept", "application/hal+json")
                .DELETE()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Then
        // Contract should get 404 status code
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(deleteRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/9999")));

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

    @Test
    @DisplayName("Contract: GET /api/products/category/{categoryId} should return product list by category ID with correct structure")
    void contractGetProductsByCategoryIdShouldReturnProductByCategoryIdWithCorrectStructure() throws Exception {

        // Given - Setup Provider contract (what API promises to return)
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

        Map<String, Object> expectedResponseList = Map.of(
                "_embedded", Map.of(
                        "productList", List.of(
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse
                        ),
                        "_links", Map.of(
                                "self", Map.of(
                                        "href", "http://localhost/api/products",
                                        "type", "GET"
                                )
                        )
                )
        );

        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/category/1"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponseList))));

        // When - Consumer makes get request
        // refactor stubForRequest method in BaseContractTest
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/category/1", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertTrue(responseBody.containsKey("_embedded"));

        // Contract validation
        Map<String, Object> embedded = (Map<String, Object>) responseBody.get("_embedded");
        assertTrue(embedded.containsKey("productList"));
        assertTrue(embedded.containsKey("_links"));

        // Contract validation
        List<Map<String, Object>> productList = (List<Map<String, Object>>) embedded.get("productList");
        assertEquals(10, productList.size());
        assertEquals(1000, productList.get(0).get("id"));
        assertEquals("New Updated Product", productList.get(0).get("name"));
        Map<String, Object> expectedCategory = Map.of(
                "id", 1,
                "name", "Beverages",
                "description", "Soft drinks, coffees, teas, beers, and ales"
        );
        assertEquals(expectedCategory, productList.get(0).get("category")); // ???
        assertTrue(productList.get(0).containsKey("_links"));

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/category/1"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/category/{categoryId} should return 404 for non-existent category ID")
    void contractGetProductsByCategoryIdShouldReturn404ForNonExistentCategoryId() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/category/9999"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/category/9999", HttpMethod.GET);

        // Then
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/category/9999"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/category/{categoryId} should return empty list when no products associated with category ID")
    void contractGetProductsByCategoryIdShouldReturnEmptyListWhenNoProductsAssociatedWithCategoryId() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/category/1"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(Map.of())))
        );

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/category/1", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertEquals(toJson(Map.of()), response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/category/1"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/category?category_name should return product list by category NAME with correct structure")
    void contractGetProductsByCategoryNameShouldReturnProductByCategoryNameWithCorrectStructure() throws Exception {

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

        Map<String, Object> expectedResponseList = Map.of(
                "_embedded", Map.of(
                        "productList", List.of(
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse
                        ),
                        "_links", Map.of(
                                "self", Map.of(
                                        "href", "http://localhost/api/products",
                                        "type", "GET"
                                )
                        )
                )
        );

        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/category?category_name=Beverages"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponseList))));

        // When - Consumer makes get request
        // refactor stubForRequest method in BaseContractTest
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/category?category_name=Beverages", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertTrue(responseBody.containsKey("_embedded"));

        // Contract validation
        Map<String, Object> embedded = (Map<String, Object>) responseBody.get("_embedded");
        assertTrue(embedded.containsKey("productList"));
        assertTrue(embedded.containsKey("_links"));

        // Contract validation
        List<Map<String, Object>> productList = (List<Map<String, Object>>) embedded.get("productList");
        assertEquals(7, productList.size());
        assertEquals(1000, productList.get(0).get("id"));
        assertEquals("New Updated Product", productList.get(0).get("name"));
        Map<String, Object> expectedCategory = Map.of(
                "id", 1,
                "name", "Beverages",
                "description", "Soft drinks, coffees, teas, beers, and ales"
        );
        assertEquals(expectedCategory, productList.get(0).get("category")); // ???
        assertTrue(productList.get(0).containsKey("_links"));

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/category?category_name=Beverages"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/category?category_name should return 404 for non-existent category NAME")
    void contractGetProductsByCategoryNameShouldReturn404ForNonExistentCategoryName() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/category?category_name=NonExistentCategory"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/category?category_name=NonExistentCategory", HttpMethod.GET);

        // Then
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/category?category_name=NonExistentCategory"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/category?category_name should return empty list when no products associated with category NAME")
    void contractGetProductsByCategoryNameShouldReturnEmptyListWhenNoProductsAssociatedWithCategoryName() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/category?category_name=Beverages"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(Map.of())))
        );

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/category?category_name=Beverages", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertEquals(toJson(Map.of()), response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/category?category_name=Beverages"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/supplier/{supplier_id} should return product list by supplier ID with correct structure")
    void contractGetProductsBySupplierIdShouldReturnProductBySupplierIdWithCorrectStructure() throws Exception {

        // Given - Setup Provider contract (what API promises to return)
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

        Map<String, Object> expectedResponseList = Map.of(
                "_embedded", Map.of(
                        "productList", List.of(
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse
                        ),
                        "_links", Map.of(
                                "self", Map.of(
                                        "href", "http://localhost/api/products",
                                        "type", "GET"
                                )
                        )
                )
        );

        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/supplier/1"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponseList))));

        // When - Consumer makes get request
        // refactor stubForRequest method in BaseContractTest
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/supplier/1", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertTrue(responseBody.containsKey("_embedded"));

        // Contract validation
        Map<String, Object> embedded = (Map<String, Object>) responseBody.get("_embedded");
        assertTrue(embedded.containsKey("productList"));
        assertTrue(embedded.containsKey("_links"));

        Map<String, Object> _links = (Map<String, Object>) embedded.get("_links");
        assertTrue(_links.containsKey("self"));

        // Contract validation
        List<Map<String, Object>> productList = (List<Map<String, Object>>) embedded.get("productList");
        assertEquals(10, productList.size());
        assertEquals(1000, productList.get(0).get("id"));
        assertEquals("New Updated Product", productList.get(0).get("name"));
        Map<String, Object> expectedSupplier = Map.of(
                "id", 1L,
                "companyName", "Exotic Liquids",
                "contactName", "Charlotte Cooper",
                "contactTitle", "Purchasing Manager",
                "address", "49 Gilbert St.",
                "country", "UK",
                "phone", "(171) 555-2222",
                "homepage", "blablahblah"
        );
        assertEquals(expectedSupplier, productList.get(0).get("supplier"));
        assertTrue(productList.get(0).containsKey("_links"));

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/supplier/1"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/supplier/{supplier_id} should return 404 for non-existent supplier ID")
    void contractGetProductsBySupplierIdShouldReturn404ForNonExistentSupplierId() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/supplier/9999"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/supplier/9999", HttpMethod.GET);

        // Then
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/supplier/9999"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/supplier/{supplier_id} should return empty list when no products associated with supplier ID")
    void contractGetProductsBySupplierIdShouldReturnEmptyListWhenNoProductsAssociatedWithSupplierId() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/supplier/1"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(Map.of())))
        );

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/supplier/1", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertEquals(toJson(Map.of()), response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/supplier/1"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/supplier?companyName should return product list by supplier company name correct structure")
    void contractGetProductsBySupplierCompanyNameShouldReturnProductBySupplierCompanyNameCorrectStructure() throws Exception {

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

        Map<String, Object> expectedResponseList = Map.of(
                "_embedded", Map.of(
                        "productList", List.of(
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse,
                                expectedResponse
                        ),
                        "_links", Map.of(
                                "self", Map.of(
                                        "href", "http://localhost/api/products",
                                        "type", "GET"
                                )
                        )
                )
        );

        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/supplier?companyName=Exotic Liquids"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(expectedResponseList))));

        // When - Consumer makes get request
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/supplier?companyName=Exotic Liquids", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertThat(response.headers().firstValue("Content-Type").orElse(""))
                .contains("application/hal+json");

        Map<String, Object> responseBody = toMap(response);
        assertTrue(responseBody.containsKey("_embedded"));

        // Contract validation
        Map<String, Object> embedded = (Map<String, Object>) responseBody.get("_embedded");
        assertTrue(embedded.containsKey("productList"));
        assertTrue(embedded.containsKey("_links"));

        // Contract validation
        List<Map<String, Object>> productList = (List<Map<String, Object>>) embedded.get("productList");
        assertEquals(10, productList.size());
        assertEquals(1000, productList.get(0).get("id"));
        assertEquals("Chai", productList.get(0).get("name"));
        Map<String, Object> expectedSupplier = Map.of(
                "id", 1L,
                "companyName", "Exotic Liquids",
                "contactName", "Charlotte Cooper",
                "contactTitle", "Purchasing Manager",
                "address", "49 Gilbert St.",
                "country", "UK",
                "phone", "(171) 555-2222",
                "homepage", "blablahblah"
        );
        assertEquals(expectedSupplier, productList.get(0).get("supplier"));
        assertTrue(productList.get(0).containsKey("_links"));

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/supplier?companyName=Exotic Liquids"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }
    @Test
    @DisplayName("Contract: GET /api/products/supplier?companyName should return 404 for non-existent supplier NAME")
    void contractGetProductsBySupplierCompanyNameShouldReturn404ForNonExistentSupplierName() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/supplier?companyName=NonExistentCompany"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.NOT_FOUND.value())));

        // When

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/supplier?companyName=NonExistentCompany", HttpMethod.GET);

        // Then
        assertEquals(404, response.statusCode());
        assertEquals("", response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/supplier?companyName=NonExistentCompany"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract: GET /api/products/supplier?companyName should return empty list when no products associated with supplier NAME")
    void contractGetProductsBySupplierCompanyNameShouldReturnEmptyListWhenNoProductsAssociatedWithSupplierName() throws Exception {

        // Given
        stubFor(get(urlEqualTo(PRODUCT_ENDPOINT + "/supplier?companyName=Exotic Liquids"))
                .withHeader("Accept", equalTo("application/hal+json"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/hal+json")
                        .withBody(toJson(Map.of())))
        );

        // When
        HttpResponse<String> response = sendRequest(PRODUCT_ENDPOINT + "/supplier?companyName=Exotic Liquids", HttpMethod.GET);

        // Then
        assertEquals(200, response.statusCode());
        assertEquals(toJson(Map.of()), response.body());

        verify(getRequestedFor(urlEqualTo(PRODUCT_ENDPOINT + "/supplier?companyName=Exotic Liquids"))
                .withHeader("Accept", equalTo("application/hal+json")));

    }

    @Test
    @DisplayName("Contract Explanation Test - This demonstrates the contract testing concept")
    void contractExplanationTest() {
        /*
         * CONTRACT TESTING EXPLANATION:
         *
         * 1. WHAT IT IS:
         *    - Tests the "contract" (API interface) between services
         *    - Ensures Producer and Consumer agree on the API structure
         *    - Prevents breaking changes between microservices
         *
         * 2. TWO SIDES:
         *    - PRODUCER (Provider): The API that serves data (our REST API)
         *    - CONSUMER: The client that uses the API (mobile app, frontend, other services)
         *
         * 3. HOW IT WORKS:
         *    - Consumer defines expectations (what it needs from the API)
         *    - Producer must fulfill those expectations (contract compliance)
         *    - If Producer changes the API, tests fail unless Consumer agrees
         *
         * 4. BENEFITS:
         *    - Early detection of breaking changes
         *    - Documentation of API expectations
         *    - Confidence in service integration
         *    - Independent development of services
         *
         * 5. TOOLS USED:
         *    - Spring Cloud Contract: Producer contract verification
         *    - WireMock: Consumer contract simulation
         *    - HTTP clients: Simulating real consumer behavior
         *
         * 6. IN THIS EXAMPLE:
         *    - WireMock simulates our Product API (Producer)
         *    - HTTP client acts as Consumer
         *    - We verify both sides honor the contract
         */

        // This test just demonstrates the concept - no actual testing
        assertThat("Contract testing").contains("Contract");
    }
}
