package com.deveyk.northwind.contract.product;

import com.deveyk.northwind.common.exception.handler.GlobalExceptionHandler;
import com.deveyk.northwind.contract.BaseContractTest;
import com.deveyk.northwind.product.controller.IProductController;
import com.deveyk.northwind.product.controller.impl.ProductController;
import com.deveyk.northwind.product.mapper.ProductMapper;
import com.deveyk.northwind.product.model.response.ProductResponse;
import com.deveyk.northwind.product.service.IProductService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public abstract class ProductBaseContractTest extends BaseContractTest {

    protected static final String PRODUCT_ENDPOINT = "/api/products";

    @Mock
    protected IProductService productService;

    @Mock
    protected ProductMapper productMapper;

    protected IProductController productController;

    @BeforeEach
    public void setUpProduct() {
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
        setupMockDataAndBehaviors();
    }

    protected abstract void setupMockDataAndBehaviors();

}
