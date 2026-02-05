package com.deveyk.northwind.unit.product.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = List.of(testProduct);
        Page<Product> productPage = new PageImpl<>(products, pageable, 1);

        when(productRepository.findAll(pageable)).thenReturn(productPage);

        // When
        Page<Product> result = productService.findAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testProduct.getName(), result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(productRepository, times(1)).findAll(pageable);

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

    @Test
    @DisplayName("Should update product successfully")
    void shouldUpdateProductSuccessfully() {

        // Given
        when(productRepository.save(testProduct)).thenReturn(testProduct);

        // When
        Product updatedProduct = productService.update(testProduct);

        // Then
        assertNotNull(updatedProduct);
        assertEquals(testProduct.getId(), updatedProduct.getId());

        verify(productRepository, times(1)).save(testProduct);

    }

    @Test
    @DisplayName("Should return product list successfully when category ID found")
    void shouldReturnProductListSuccessfullyWhenCategoryFoundById() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = List.of(testProduct);
        Page<Product> productPage = new PageImpl<>(products, pageable, 1);

        when(categoryRepository.findById(testCategory.getId())).thenReturn(Optional.of(testCategory));
        when(productRepository.findByCategoryId(testCategory.getId(), pageable)).thenReturn(productPage);

        // When
        Page<Product> result = productService.findByCategoryId(testCategory.getId(), pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testProduct.getName(), result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(categoryRepository, times(1)).findById(testCategory.getId());
        verify(productRepository, times(1)).findByCategoryId(testCategory.getId(), pageable);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when category ID not found")
    void shouldThrowEntityNotFoundExceptionWhenCategoryNotFound() {
        // Given
        Long invalidCategoryId = 9999L;
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryRepository.findById(invalidCategoryId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.findByCategoryId(invalidCategoryId, pageable))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Category not found");

        verify(categoryRepository, times(1)).findById(invalidCategoryId);
        verify(productRepository, never()).findByCategoryId(invalidCategoryId, pageable);
    }

    @Test
    @DisplayName("Should return empty product list when no products associated with category ID")
    void shouldReturnEmptyProductListWhenNoProductsAssociatedWithCategory() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyProductPage = new PageImpl<>(List.of(), pageable, 0);
        when(categoryRepository.findById(testCategory.getId())).thenReturn(Optional.of(testCategory));
        when(productRepository.findByCategoryId(testCategory.getId(), pageable)).thenReturn(emptyProductPage);

        // When
        Page<Product> result = productService.findByCategoryId(testCategory.getId(), pageable);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());

        verify(categoryRepository, times(1)).findById(testCategory.getId());
        verify(productRepository, times(1)).findByCategoryId(testCategory.getId(), pageable);
    }

    @Test
    @DisplayName("Should return product list successfully when category NAME found")
    void shouldReturnProductListSuccessfullyWhenCategoryFoundByName() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = List.of(testProduct);
        Page<Product> productPage = new PageImpl<>(products, pageable, 1);

        when(categoryRepository.findByName(testCategory.getName())).thenReturn(Optional.of(testCategory));
        when(productRepository.findByCategoryName(testCategory.getName(), pageable)).thenReturn(productPage);

        // When
        Page<Product> result = productService.findByCategoryName(testCategory.getName(), pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testProduct.getName(), result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(categoryRepository, times(1)).findByName(testCategory.getName());
        verify(productRepository, times(1)).findByCategoryName(testCategory.getName(), pageable);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when category NAME not found")
    void shouldThrowEntityNotFoundExceptionWhenCategoryNotFoundByName() {

        // Given
        String invalidCategoryName = "invalidCategoryName";
        Pageable pageable = PageRequest.of(0, 10);
        when(categoryRepository.findByName(invalidCategoryName)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.findByCategoryName(invalidCategoryName, pageable))
                        .isInstanceOf(EntityNotFoundException.class)
                        .hasMessage("Category not found");

       verify(categoryRepository, times(1)).findByName(invalidCategoryName);
       verify(productRepository, never()).findByCategoryName(invalidCategoryName, pageable);

    }

    @Test
    @DisplayName("Should return empty product list when no products associated with category NAME")
    void shouldReturnEmptyProductListWhenNoProductsAssociatedWithCategoryByName() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyProductPage = new PageImpl<>(List.of(), pageable, 0);
        when(categoryRepository.findByName(testCategory.getName())).thenReturn(Optional.of(testCategory));
        when(productRepository.findByCategoryName(testCategory.getName(), pageable)).thenReturn(emptyProductPage);

        // When
        Page<Product> result = productService.findByCategoryName(testCategory.getName(), pageable);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());

        verify(categoryRepository, times(1)).findByName(testCategory.getName());
        verify(productRepository, times(1)).findByCategoryName(testCategory.getName(), pageable);

    }

    @Test
    @DisplayName("Should return product list successfully when supplier ID found")
    void shouldReturnProductListSuccessfullyWhenSupplierFoundById() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = List.of(testProduct);
        Page<Product> productPage = new PageImpl<>(products, pageable, 1);

        when(supplierRepository.findById(testSupplier.getId())).thenReturn(Optional.of(testSupplier));
        when(productRepository.findBySupplierId(testSupplier.getId(), pageable)).thenReturn(productPage);

        // When
        Page<Product> result = productService.findBySupplierId(testSupplier.getId(), pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testProduct.getName(), result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(supplierRepository, times(1)).findById(testSupplier.getId());
        verify(productRepository, times(1)).findBySupplierId(testSupplier.getId(), pageable);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when supplier ID not found")
    void shouldThrowEntityNotFoundExceptionWhenSupplierNotFound() {

        // Given
        Long invalidSupplierId = 9999L;
        Pageable pageable = PageRequest.of(0, 10);
        when(supplierRepository.findById(invalidSupplierId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.findBySupplierId(invalidSupplierId, pageable))
                        .isInstanceOf(EntityNotFoundException.class)
                        .hasMessage("Supplier not found");

       verify(supplierRepository, times(1)).findById(invalidSupplierId);
       verify(productRepository, never()).findBySupplierId(invalidSupplierId, pageable);

    }

    @Test
    @DisplayName("Should return empty product list when no products associated with supplier ID")
    void shouldReturnEmptyProductListWhenNoProductsAssociatedWithSupplier() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyProductPage = new PageImpl<>(List.of(), pageable, 0);
        when(supplierRepository.findById(testSupplier.getId())).thenReturn(Optional.of(testSupplier));
        when(productRepository.findBySupplierId(testSupplier.getId(), pageable)).thenReturn(emptyProductPage);

        // When
        Page<Product> result = productService.findBySupplierId(testSupplier.getId(), pageable);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());

        verify(supplierRepository, times(1)).findById(testSupplier.getId());
        verify(productRepository, times(1)).findBySupplierId(testSupplier.getId(), pageable);
    }

    @Test
    @DisplayName("Should return product list successfully when supplier NAME found")
    void shouldReturnProductListSuccessfullyWhenSupplierNameFound() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = List.of(testProduct);
        Page<Product> productPage = new PageImpl<>(products, pageable, 1);

        when(supplierRepository.findByCompanyName(testSupplier.getCompanyName())).thenReturn(Optional.of(testSupplier));
        when(productRepository.findBySupplierCompanyName(testSupplier.getCompanyName(), pageable)).thenReturn(productPage);

        // When
        Page<Product> result = productService.findBySupplierCompanyName(testSupplier.getCompanyName(), pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testProduct.getName(), result.getContent().get(0).getName());

        verify(supplierRepository, times(1)).findByCompanyName(testSupplier.getCompanyName());
        verify(productRepository, times(1)).findBySupplierCompanyName(testSupplier.getCompanyName(), pageable);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when supplier COMPANY NAME not found")
    void shouldThrowEntityNotFoundExceptionWhenSupplierCompanyNameNotFound() {

        // Given
        String invalidSupplierName = "invalidSupplierName";
        Pageable pageable = PageRequest.of(0, 10);
        when(supplierRepository.findByCompanyName(invalidSupplierName)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> productService.findBySupplierCompanyName(invalidSupplierName, pageable))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Supplier not found");

        verify(supplierRepository, times(1)).findByCompanyName(invalidSupplierName);
        verify(productRepository, never()).findBySupplierCompanyName(invalidSupplierName, pageable);


    }

    @Test
    @DisplayName("Should return empty product list when no products associated with supplier COMPANY NAME")
    void shouldReturnEmptyProductListWhenNoProductsAssociatedWithSupplierCompanyName() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyProductPage = new PageImpl<>(List.of(), pageable, 0);
        when(supplierRepository.findByCompanyName(testSupplier.getCompanyName())).thenReturn(Optional.of(testSupplier));
        when(productRepository.findBySupplierCompanyName(testSupplier.getCompanyName(), pageable)).thenReturn(emptyProductPage);

        // When
        Page<Product> result = productService.findBySupplierCompanyName(testSupplier.getCompanyName(), pageable);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());

        verify(supplierRepository, times(1)).findByCompanyName(testSupplier.getCompanyName());
        verify(productRepository, times(1)).findBySupplierCompanyName(testSupplier.getCompanyName(), pageable);

    }

    @Test
    @DisplayName("Should return list of products with low stock")
    void shouldReturnLowStockProducts() {
        // Given
        Product lowStockProduct = Product.builder()
                .id(1001L)
                .name("Aniseed Syrup")
                .category(testCategory)
                .supplier(testSupplier)
                .sku("SP75018")
                .barcode("BAR1000000002")
                .quantityPerUnit("12 bottles")
                .price(BigDecimal.valueOf(10.00))
                .unitsInStock(2L) // Low stock
                .reorderLevel(5L)
                .discontinued(false)
                .build();

        Pageable pageable = PageRequest.of(0, 10);
        List<Product> products = List.of(lowStockProduct);
        Page<Product> lowStockPage = new PageImpl<>(products, pageable, 1);

        when(productRepository.findLowStock(pageable)).thenReturn(lowStockPage);

        // When
        Page<Product> result = productService.findLowStock(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(lowStockProduct.getName(), result.getContent().get(0).getName());
        verify(productRepository, times(1)).findLowStock(pageable);
    }

    // ???????????
    @Test
    @DisplayName("Should return empty list when no low stock products are found")
    void shouldReturnEmptyListForLowStockProducts() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> emptyPage = new PageImpl<>(List.of(), pageable, 0);
        when(productRepository.findLowStock(pageable)).thenReturn(emptyPage);

        // When
        Page<Product> result = productService.findLowStock(pageable);

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        assertEquals(0, result.getTotalElements());
        verify(productRepository, times(1)).findLowStock(pageable);
    }
}
