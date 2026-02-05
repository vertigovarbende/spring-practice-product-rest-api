package com.deveyk.northwind.product.service;

import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.CategoryRepository;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.repository.SupplierRepository;
import com.deveyk.northwind.product.service.impl.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("ProductService Unit Tests")
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;
    private Category testCategory;
    private Supplier testSupplier;

    @BeforeEach
    void setUp() {

        testCategory = Category.builder()
                .id(1L)
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
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

        productService = new ProductService(productRepository, categoryRepository, supplierRepository);
    }

    @Test
    @DisplayName("Should return product when found by ID successfully")
    void shouldFindProductByIdSuccessfully() {
        // Given
        Long productId = 1000L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(testProduct));

        // When
        Product foundProduct = productService.findById(productId);

        // Then
        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
        assertEquals(testProduct.getName(), foundProduct.getName());

        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product ID not found")
    void shouldThrowEntityNotFoundExceptionWhenProductNotFoundById() {
        // Given
        Long productId = 9999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.findById(productId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product not found");

        verify(productRepository, times(1)).findById(productId);

    }

    @Test
    @DisplayName("Should return product list successfully")
    void shouldReturnProductListSuccessfully() {
        // Given
        when(productRepository.findAll()).thenReturn(List.of(any(Product.class), any(Product.class), any(Product.class)));

    }

    @Test
    @DisplayName("Should save product successfully when category and supplier exists")
    void shouldSaveProductSuccessfully() {

        // Given
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(testCategory));
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(testSupplier));
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // When
        Product savedProduct = productService.save(testProduct);

        // Then
        assertNotNull(savedProduct);
        assertEquals(testProduct.getName(), savedProduct.getName());
        assertEquals(testCategory.getName(), savedProduct.getCategory().getName());
        assertEquals(testSupplier.getCompanyName(), savedProduct.getSupplier().getCompanyName());
        // assertThat(savedProduct).isNotNull();
        // assertThat(savedProduct.getName()).isEqualTo(testProduct.getName());
        // assertThat(savedProduct.getCategory.getName()).isEqualTo(testCategory.getName());
        // assertThat(savedProduct.getSupplier.getName()).isEqualTo(testSupplier.getName());

        verify(categoryRepository, times(1)).findById(testCategory.getId());
        verify(supplierRepository, times(1)).findById(testSupplier.getId());
        verify(productRepository, times(1)).save(any(Product.class));

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when category ID not found")
    void shouldThrowEntityNotFoundExceptionWhenCategoryNotFoundById() {

        // Given
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.save(testProduct))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Category not found");

        verify(categoryRepository, times(1)).findById(testCategory.getId());
        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when supplier ID not found")
    void shouldThrowEntityNotFoundExceptionWhenSupplierNotFoundById() {
        // Given
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(testCategory));
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.save(testProduct))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Supplier not found");

        verify(supplierRepository, times(1)).findById(testSupplier.getId());
        verify(categoryRepository, times(1)).findById(testCategory.getId());
        verify(productRepository, never()).save(any(Product.class));

    }

    @Test
    @DisplayName("Should delete product by id successfully")
    void shouldDeleteProductByIdSuccessfully() {
        // Given
        Long productId = 1000L;
        when(productRepository.findById(productId)).thenReturn(Optional.of(testProduct));

        // When
        productService.deleteById(productId);

        // Then
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when product ID not found")
    void shouldThrowEntityNotFoundExceptionWhenProductNotFound() {

        // Given
        Long productId = 9999L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> productService.deleteById(productId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Product not found");

        // Then
        verify(productRepository, times(1)).findById(productId);
        verify(productRepository, never()).deleteById(productId);

    }


}
