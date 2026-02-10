package com.deveyk.northwind.contract;

import com.deveyk.northwind.common.exception.handler.GlobalExceptionHandler;
import com.deveyk.northwind.product.controller.IProductController;
import com.deveyk.northwind.product.controller.impl.ProductController;
import com.deveyk.northwind.product.mapper.ProductMapper;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.model.response.CategoryResponse;
import com.deveyk.northwind.product.model.response.ProductResponse;
import com.deveyk.northwind.product.model.response.SupplierResponse;
import com.deveyk.northwind.product.service.IProductService;
import com.deveyk.northwind.product.service.impl.ProductService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public abstract class BaseContractTest {

    @Mock
    IProductService productService;

    @Mock
    ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Create controller with mocked dependencies
        IProductController productController = new ProductController(productService, productMapper);

        // Setup standalone MockMvc with exception handler and custom argument resolvers
        MockMvc mvcMock = MockMvcBuilders
                .standaloneSetup(productController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        RestAssuredMockMvc.mockMvc(mvcMock);
        mockDataSetupAndServiceBehavior();
    }

    private void mockDataSetupAndServiceBehavior() {
        // TEST DATA

        // Create Category
        Category category = Category.builder()
                .id(1L)
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
                .build();

        // Create CategoryResponse
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();

        // Create Supplier
        Supplier supplier = Supplier.builder()
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
        SupplierResponse supplierResponse = SupplierResponse.builder()
                .id(supplier.getId())
                .companyName(supplier.getCompanyName())
                .contactName(supplier.getContactName())
                .contactTitle(supplier.getContactTitle())
                .address(supplier.getAddress())
                .country(supplier.getCountry())
                .phone(supplier.getPhone())
                .homepage(supplier.getHomepage())
                .build();

        // Create Product
        Product product = Product.builder()
                .id(1000L)
                .name("Chai")
                .category(category)
                .supplier(supplier)
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
        ProductResponse productResponse = ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .category(categoryResponse)
                .supplier(supplierResponse)
                .sku(product.getSku())
                .barcode(product.getBarcode())
                .quantityPerUnit(product.getQuantityPerUnit())
                .price(product.getPrice())
                .unitsInStock(product.getUnitsInStock())
                .unitsOnOrder(product.getUnitsOnOrder())
                .build();

        // MOCK SERVICE BEHAVIORS

        // Mock for find product GET /api/products/1
        given(productService.findById(1000L)).willReturn(product);
        given(productMapper.toResponse(product)).willReturn(productResponse);

        // Mock for GET /api/products/9999 (it will throw EntityNotFoundException for proper 404 handling)
        given(productService.findById(9999L)).willThrow(new EntityNotFoundException("Product not found"));

        // Mock for GET /api/products ????
        given(productService.findAll(any(Pageable.class))).willReturn(new PageImpl<>(List.of(product)));

        // Mock for create product POST /api/products
        given(productMapper.toEntity(any(ProductRequest.class))).willReturn(product);
        given(productService.save(any(Product.class))).willReturn(product);
        given(productMapper.toResponse(any(Product.class))).willReturn(productResponse);

        // Mock for update product PUT /api/products/1
        given(productService.update(any(Product.class))).willReturn(product);

        // Mock for delete product DELETE /api/products/1 - no return value needed

    }


}
