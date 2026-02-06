package com.deveyk.northwind.unit.product.service;

import com.deveyk.northwind.product.exception.CannotDeleteSupplierException;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.repository.SupplierRepository;
import com.deveyk.northwind.product.service.impl.SupplierService;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@DisplayName("SupplierService Unit Tests")
public class SupplierServiceTest {

    @Mock
    private SupplierRepository supplierRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SupplierService supplierService;

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

        supplierService = new SupplierService(supplierRepository, productRepository);

    }

    @Test
    @DisplayName("Should return supplier when found by ID successfully")
    void shouldFindSupplierByIdSuccessfully() {

        // Given
        Long supplierId = 1L;
        when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(testSupplier));

        // When
        Supplier foundSupplier = supplierService.findById(supplierId);

        // Then
        assertNotNull(foundSupplier);
        assertEquals(supplierId, foundSupplier.getId());
        assertEquals(testSupplier.getCompanyName(), foundSupplier.getCompanyName());

        verify(supplierRepository, times(1)).findById(supplierId);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when supplier ID not found")
    void shouldThrowEntityNotFoundExceptionWhenSupplierNotFoundById() {

        // Given
        Long invalidSupplierId = 9999L;
        when(supplierRepository.findById(invalidSupplierId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> supplierService.findById(invalidSupplierId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Supplier not found");

        verify(supplierRepository, times(1)).findById(invalidSupplierId);

    }

    @Test
    @DisplayName("Should return supplier list successfully")
    void shouldReturnSupplierListSuccessfully() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Supplier> suppliers = List.of(testSupplier);
        Page<Supplier> supplierPage = new PageImpl<>(suppliers, pageable, 1);

        when(supplierRepository.findAll(pageable)).thenReturn(supplierPage);

        // When
        Page<Supplier> result = supplierService.findAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testSupplier.getCompanyName(), result.getContent().get(0).getCompanyName());
        assertEquals(1, result.getTotalElements());

        verify(supplierRepository, times(1)).findAll(pageable);

    }

    @Test
    @DisplayName("Should return empty page when no suppliers available")
    void shouldReturnEmptyPageWhenNoSuppliersAvailable() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> emptySupplierPage = new PageImpl<>(List.of(), pageable, 0);

        when(supplierRepository.findAll(pageable)).thenReturn(emptySupplierPage);

        // When
        Page<Supplier> result = supplierService.findAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());

        verify(supplierRepository, times(1)).findAll(pageable);

    }

    @Test
    @DisplayName("Should save supplier successfully when products exists")
    void shouldSaveSupplierSuccessfully() {

        // Given
        when(supplierRepository.save(any(Supplier.class))).thenReturn(testSupplier);

        // When
        Supplier savedSupplier = supplierService.save(testSupplier);

        // Then
        assertNotNull(savedSupplier);
        assertEquals(testSupplier.getCompanyName(), savedSupplier.getCompanyName());

        verify(supplierRepository, times(1)).save(any(Supplier.class));

    }

    @Test
    @DisplayName("Should update supplier successfully")
    void shouldUpdateSupplierSuccessfully() {

        // Given
        when(supplierRepository.save(any(Supplier.class))).thenReturn(testSupplier);

        // When
        Supplier updatedSupplier = supplierService.update(testSupplier);

        // Then
        assertNotNull(updatedSupplier);
        assertEquals(testSupplier.getCompanyName(), updatedSupplier.getCompanyName());

        verify(supplierRepository, times(1)).save(any(Supplier.class));

    }

    @Test
    @DisplayName("Should delete supplier successfully when it exists, and no products are associated")
    void shouldDeleteSupplierSuccessfully() {

        // Given
        Long supplierId = 1L;
        when(supplierRepository.existsById(supplierId)).thenReturn(true);
       // when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(testSupplier));
        when(productRepository.countBySupplierId(supplierId)).thenReturn(0L);

        // When
        supplierService.deleteById(supplierId);

        // Then
        verify(productRepository, times(1)).countBySupplierId(supplierId);
        verify(supplierRepository, times(1)).deleteById(supplierId);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when trying to delete a non-existent supplier")
    void shouldThrowEntityNotFoundExceptionWhenSupplierDoesNotExist() {
        // Given
        Long invalidSupplierId = 9999L;
        when(supplierRepository.existsById(invalidSupplierId)).thenReturn(false);

        // When/Then
        assertThatThrownBy(() -> supplierService.deleteById(invalidSupplierId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Supplier not found");

        verify(supplierRepository, times(1)).existsById(invalidSupplierId);
        verifyNoInteractions(productRepository);
    }

    @Test
    @DisplayName("Should throw CannotDeleteSupplierException when supplier has associated products")
    void shouldThrowCannotDeleteSupplierExceptionWhenProductsExist() {
        // Given
        Long supplierId = 1L;
        when(supplierRepository.existsById(supplierId)).thenReturn(true);
        when(productRepository.countBySupplierId(supplierId)).thenReturn(5L);

        // When/Then
        assertThatThrownBy(() -> supplierService.deleteById(supplierId))
                .isInstanceOf(CannotDeleteSupplierException.class)
                .hasMessage("Cannot delete supplier that has products. Product count: 5");

        verify(supplierRepository, times(1)).existsById(supplierId);
        verify(productRepository, times(1)).countBySupplierId(supplierId);
        verify(supplierRepository, never()).deleteById(supplierId);
    }

    @Test
    @DisplayName("Should return product count successfully when supplier found by ID")
    void shouldReturnProductCountSuccessfullyWhenSupplierExists() {
        // Given
        Long supplierId = 1L;
        long productCount = 5L;
        when(supplierRepository.existsById(supplierId)).thenReturn(true);
        // when(supplierRepository.findById(supplierId)).thenReturn(Optional.of(testSupplier));
        when(productRepository.countBySupplierId(supplierId)).thenReturn(productCount);

        // When
        long result = supplierService.getProductCountBySupplierId(supplierId);

        // Then
        assertEquals(productCount, result);

        verify(supplierRepository, times(1)).existsById(supplierId);
        verify(productRepository, times(1)).countBySupplierId(supplierId);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when supplier does not exist for product count")
    void shouldThrowEntityNotFoundExceptionWhenSupplierDoesNotExistsForProductCount() {
        // Given
        Long invalidSupplierId = 9999L;
        when(supplierRepository.existsById(invalidSupplierId)).thenReturn(false);

        // When/Then
        assertThatThrownBy(() -> supplierService.getProductCountBySupplierId(invalidSupplierId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Supplier not found");

        verify(supplierRepository, times(1)).existsById(invalidSupplierId);
        verifyNoInteractions(productRepository);
    }

    @Test
    @DisplayName("Should find suppliers by country successfully")
    void shouldFindSuppliersByCountrySuccessfully() {
        // Given
        String validCountryName = "UK";
        Pageable pageable = PageRequest.of(0, 10);
        List<Supplier> suppliers = List.of(testSupplier);
        Page<Supplier> supplierPage = new PageImpl<>(suppliers, pageable, suppliers.size());

        when(supplierRepository.findByCountry(validCountryName, pageable)).thenReturn(supplierPage);

        // When
        Page<Supplier> result = supplierService.findByCountry(validCountryName, pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(validCountryName, result.getContent().get(0).getCountry());

        verify(supplierRepository, times(1)).findByCountry(validCountryName, pageable);
    }

    @Test
    @DisplayName("Should return empty page when no suppliers found for a country")
    void shouldReturnEmptyPageWhenNoSuppliersFoundForCountry() {
        // Given
        String invalidCountry = "NonExistentCountry";
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> emptySupplierPage = new PageImpl<>(List.of(), pageable, 0);

        when(supplierRepository.findByCountry(invalidCountry, pageable)).thenReturn(emptySupplierPage);

        // When
        Page<Supplier> result = supplierService.findByCountry(invalidCountry, pageable);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());

        verify(supplierRepository, times(1)).findByCountry(invalidCountry, pageable);
    }

    @Test
    @DisplayName("Should find popular suppliers successfully")
    void shouldFindPopularSuppliersSuccessfully() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Supplier> popularSuppliers = List.of(testSupplier);
        Page<Supplier> popularSupplierPage = new PageImpl<>(popularSuppliers, pageable, popularSuppliers.size());

        when(supplierRepository.findPopularSuppliers(pageable)).thenReturn(popularSupplierPage);

        // When
        Page<Supplier> result = supplierService.findPopularSuppliers(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testSupplier.getCompanyName(), result.getContent().get(0).getCompanyName());

        verify(supplierRepository, times(1)).findPopularSuppliers(pageable);
    }

    @Test
    @DisplayName("Should return empty page when no popular suppliers exist")
    void shouldReturnEmptyPageWhenNoPopularSuppliersExist() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Supplier> emptySupplierPage = new PageImpl<>(List.of(), pageable, 0);

        when(supplierRepository.findPopularSuppliers(pageable)).thenReturn(emptySupplierPage);

        // When
        Page<Supplier> result = supplierService.findPopularSuppliers(pageable);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());

        verify(supplierRepository, times(1)).findPopularSuppliers(pageable);
    }
}
