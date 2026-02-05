package com.deveyk.northwind.unit.product.controller;

import com.deveyk.northwind.product.controller.impl.ProductController;
import com.deveyk.northwind.product.mapper.ProductMapper;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.model.response.CategoryResponse;
import com.deveyk.northwind.product.model.response.ProductResponse;
import com.deveyk.northwind.product.model.response.SupplierResponse;
import com.deveyk.northwind.product.service.impl.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ProductController Unit Tests")
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductController productController;

    private Product testProduct;
    private Category testCategory;
    private Supplier testSupplier;
    private ProductResponse mockProductResponse;
    private CategoryResponse mockCategoryResponse;
    private SupplierResponse mockSupplierResponse;


    @BeforeEach
    void setUp() {

        testCategory = Category.builder()
                .id(1L)
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
                .build();

        mockCategoryResponse = CategoryResponse.builder()
                .id(testCategory.getId())
                .name(testCategory.getName())
                .description(testCategory.getDescription())
                .build();

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

        mockSupplierResponse = SupplierResponse.builder()
                .id(testSupplier.getId())
                .companyName(testSupplier.getCompanyName())
                .contactName(testSupplier.getContactName())
                .contactTitle(testSupplier.getContactTitle())
                .address(testSupplier.getAddress())
                .country(testSupplier.getCountry())
                .phone(testSupplier.getPhone())
                .homepage(testSupplier.getHomepage())
                .build();

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

        mockProductResponse = ProductResponse.builder()
                .id(testProduct.getId())
                .name(testProduct.getName())
                .category(mockCategoryResponse)
                .supplier(mockSupplierResponse)
                .sku(testProduct.getSku())
                .barcode(testProduct.getBarcode())
                .quantityPerUnit(testProduct.getQuantityPerUnit())
                .price(testProduct.getPrice())
                .unitsInStock(testProduct.getUnitsInStock())
                .unitsOnOrder(testProduct.getUnitsOnOrder())
                .build();

        productController = new ProductController(productService, productMapper);
    }

    /**
     * <p>
     *     Tests the functionality of retrieving a product by its ID successfully via the ProductController.
     * </p>
     * This test method verifies:
     * <ol>
     *   <li>The {@code productService.findById} method is called once with the provided product ID.</li>
     *   <li>The {@code productMapper.toResponse} method is invoked with the product returned by the service.</li>
     *   <li>
     *     The response from the {@code productController.get} method contains:
     *     <ul>
     *       <li>A non-null response body.</li>
     *       <li>An HTTP status of 200 (OK).</li>
     *       <li>The expected {@code ProductResponse} object.</li>
     *     </ul>
     *   </li>
     * </ol>
     */
    @Test
    @DisplayName("Get Product by ID - Success")
    void shouldFindProductByIdSuccessfully() {

        Long productId = 1000L;

        // Given
        when(productService.findById(productId)).thenReturn(testProduct);
        when(productMapper.toResponse(testProduct)).thenReturn(mockProductResponse);

        // When
        ResponseEntity<EntityModel<ProductResponse>> response = productController.get(productId);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isEqualTo(mockProductResponse);

        verify(productService, times(1)).findById(productId);
        verify(productMapper, times(1)).toResponse(testProduct);
    }

    /**
     * Tests the functionality of listing products successfully through the ProductController.
     *
     * <p>This test method verifies:</p>
     * <ol>
     *   <li>
     *     The product list is retrieved successfully from the {@code productService}
     *     with pagination.
     *   </li>
     *   <li>
     *     A single product is mapped to its corresponding {@code ProductResponse}
     *     using the {@code productMapper}.
     *   </li>
     *   <li>
     *     The response from the {@code ProductController} contains:
     *     <ul>
     *       <li>A non-null HTTP response.</li>
     *       <li>A status code of 200 (OK).</li>
     *       <li>A non-null response body.</li>
     *       <li>The expected number of {@code ProductResponse} objects in the response body.</li>
     *     </ul>
     *   </li>
     *   <li>
     *     The {@code productService.findAll} and {@code productMapper.toResponse}
     *     methods are called exactly once.
     *   </li>
     * </ol>
     */
    @Test
    @DisplayName("List Products - Success")
    void shouldListProductsSuccessfully() {
        // Given
        List<Product> productList = List.of(testProduct);
        Page<Product> productPage = new PageImpl<>(productList);
        when(productService.findAll(any(Pageable.class))).thenReturn(productPage);
        when(productMapper.toResponse(testProduct)).thenReturn(mockProductResponse);

        // When
        ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> response = productController.list(Pageable.unpaged());

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).hasSize(1);

        verify(productService, times(1)).findAll(any(Pageable.class));
        verify(productMapper, times(1)).toResponse(testProduct);
    }

    /**
     * Tests the functionality of creating a new product successfully through the {@code ProductController}.
     *
     * <p>This test ensures:</p>
     * <ol>
     *   <li>
     *     The {@code productMapper.toEntity} method is invoked with the correct
     *     {@code ProductRequest}.
     *   </li>
     *   <li>
     *     The {@code productService.save} method is called with the appropriate
     *     product entity.
     *   </li>
     *   <li>
     *     The {@code productMapper.toResponse} method is used to map the saved
     *     product entity to a response object.
     *   </li>
     *   <li>
     *     The response from the {@code ProductController.create} method includes:
     *     <ul>
     *       <li>A non-null response.</li>
     *       <li>An HTTP status of 201 (Created).</li>
     *       <li>
     *         A response body that is not null and contains the expected
     *         {@code ProductResponse} object.
     *       </li>
     *     </ul>
     *   </li>
     *   <li>
     *     All mapped and service-layer methods involved are verified to have been
     *     called exactly once.
     *   </li>
     * </ol>
     */
    @Test
    @DisplayName("Create Product - Success")
    void shouldCreateProductSuccessfully() {

        // Given
        ProductRequest productRequest = ProductRequest.builder()
                .name(testProduct.getName())
                .category(testCategory.getName())
                .supplier(testSupplier.getCompanyName())
                .sku(testProduct.getSku())
                .barcode(testProduct.getBarcode())
                .price(Double.valueOf(testProduct.getPrice().toString()))
                .quantityPerUnit(testProduct.getQuantityPerUnit())
                .unitsInStock(testProduct.getUnitsInStock())
                .build();
        when(productMapper.toEntity(productRequest)).thenReturn(testProduct);
        when(productService.save(testProduct)).thenReturn(testProduct);
        when(productMapper.toResponse(testProduct)).thenReturn(mockProductResponse);

        // When
        ResponseEntity<EntityModel<ProductResponse>> response = productController.create(productRequest);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isEqualTo(mockProductResponse);

        verify(productMapper, times(1)).toEntity(productRequest);
        verify(productService, times(1)).save(testProduct);
        verify(productMapper, times(1)).toResponse(testProduct);

    }

    // ??????????????????
    @Test
    @DisplayName("Update Product - Success")
    void shouldUpdateProductSuccessfully() {
        // Given
        Long productId = 1000L;
        ProductRequest productRequest = ProductRequest.builder()
                .name(testProduct.getName())
                .category(testCategory.getName())
                .supplier(testSupplier.getCompanyName())
                .sku(testProduct.getSku())
                .barcode(testProduct.getBarcode())
                .price(Double.valueOf(testProduct.getPrice().toString()))
                .quantityPerUnit(testProduct.getQuantityPerUnit())
                .unitsInStock(testProduct.getUnitsInStock())
                .build();

        when(productMapper.toEntity(productRequest)).thenReturn(testProduct);
        when(productService.update(testProduct)).thenReturn(testProduct);
        when(productMapper.toResponse(testProduct)).thenReturn(mockProductResponse);

        // When
        ResponseEntity<EntityModel<ProductResponse>> response = productController.update(productId, productRequest);

        // Then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getContent()).isEqualTo(mockProductResponse);

        verify(productMapper, times(1)).toEntity(productRequest);
        verify(productService, times(1)).update(testProduct);
        verify(productMapper, times(1)).toResponse(testProduct);
    }



}
