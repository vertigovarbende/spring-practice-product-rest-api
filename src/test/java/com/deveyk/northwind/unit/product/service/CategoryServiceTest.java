package com.deveyk.northwind.unit.product.service;

import com.deveyk.northwind.product.exception.CannotDeleteCategoryException;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.CategoryRepository;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.service.impl.CategoryService;
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
@DisplayName("CategoryService Unit Tests")
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category testCategory;
    private Product testProduct;
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


    }

    @Test
    @DisplayName("Should return category when found by ID successfully")
    void shouldFindCategoryByIdSuccessfully() {

        // Given
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(testCategory));

        // When
        Category foundCategory = categoryService.findById(categoryId);

        // Then
        assertNotNull(foundCategory);
        assertEquals(categoryId, foundCategory.getId());
        assertEquals(testCategory.getName(), foundCategory.getName());

        verify(categoryRepository, times(1)).findById(categoryId);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when category ID not found")
    void shouldThrowEntityNotFoundExceptionWhenCategoryNotFoundById() {

        // Given
        Long categoryId = 9999L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // When/Then
        assertThatThrownBy(() -> categoryService.findById(categoryId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Category not found");

    }

    @Test
    @DisplayName("Should return category list successfully")
    void shouldReturnCategoryListSuccessfully() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Category> categories = List.of(testCategory);
        Page<Category> categoryPage = new PageImpl<>(categories, pageable, 1);

        when(categoryRepository.findAll(pageable)).thenReturn(categoryPage);

        // When
        Page<Category> result = categoryService.findAll(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testCategory.getName(), result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(categoryRepository, times(1)).findAll(pageable);

    }

    @Test
    @DisplayName("Should save category successfully when products exists")
    void shouldSaveCategorySuccessfully() {

        // Given
        when(categoryRepository.save(any(Category.class))).thenReturn(testCategory);

        // When
        Category savedCategory = categoryService.save(testCategory);

        // Then
        assertNotNull(savedCategory);
        assertEquals(testCategory.getName(), savedCategory.getName());

        verify(categoryRepository, times(1)).save(any(Category.class));

    }

    @Test
    @DisplayName("Should update category successfully")
    void shouldUpdateCategorySuccessfully() {

        // Given
        when(categoryRepository.save(testCategory)).thenReturn(testCategory);

        // When
        Category updatedCategory = categoryService.update(testCategory);

        // Then
        assertNotNull(updatedCategory);
        assertEquals(testCategory.getName(), updatedCategory.getName());

        verify(categoryRepository, times(1)).save(testCategory);

    }

    @Test
    @DisplayName("Should delete category successfully when it exists, and no products are associated")
    void shouldDeleteCategorySuccessfully() {

        // Given
        Long categoryId = 1L;
        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        when(productRepository.countByCategoryId(categoryId)).thenReturn(0L);

        // When
        categoryService.deleteById(categoryId);

        // Then
        verify(productRepository, times(1)).countByCategoryId(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when category does not exist")
    void shouldThrowEntityNotFoundExceptionWhenCategoryDoesNotExist() {

        // Given
        Long categoryId = 9999L;
        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        // When/Then
        assertThatThrownBy(() -> categoryService.deleteById(categoryId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Category not found");

        verify(categoryRepository, never()).deleteById(categoryId);
        verify(productRepository, never()).countByCategoryId(categoryId);
    }

    @Test
    @DisplayName("Should throw CannotDeleteCategoryException when products are associated with category")
    void shouldThrowCannotDeleteCategoryExceptionWhenProductsExist() {

        // Given
        Long categoryId = 1L;
        Long productCount = 5L;
        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        when(productRepository.countByCategoryId(categoryId)).thenReturn(productCount);

        // When/Then
        assertThatThrownBy(() -> categoryService.deleteById(categoryId))
                .isInstanceOf(CannotDeleteCategoryException.class)
                .hasMessage("Cannot delete category that has products. Product count: " + productCount);

        verify(categoryRepository, never()).deleteById(categoryId);
        verify(productRepository, times(1)).countByCategoryId(categoryId);

    }

    @Test
    @DisplayName("Should return product count when category count by ID successfully")
    void shouldReturnProductCountWhenCategoryCountByIdSuccessfully() {

        // Given
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(testCategory));
        // when(categoryRepository.existsById(categoryId)).thenReturn(true);
        when(productRepository.countByCategoryId(categoryId)).thenReturn(5L);

        // When
        Long productCount = categoryService.getProductCountByCategoryId(categoryId);

        // Then
        assertEquals(5L, productCount);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(productRepository, times(1)).countByCategoryId(categoryId);

    }

    @Test
    @DisplayName("Should throw EntityNotFoundException when category ID not found for product count")
    void shouldThrowEntityNotFoundExceptionWhenCategoryNotFoundByIdForProductCount() {

        // Given
        Long invalidCategoryId = 9999L;
        when(categoryRepository.findById(invalidCategoryId)).thenReturn(Optional.empty());

        // When
        assertThatThrownBy(() -> categoryService.getProductCountByCategoryId(invalidCategoryId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Category not found");

        // Then
        verify(categoryRepository, times(1)).findById(invalidCategoryId);
        verify(productRepository, never()).countByCategoryId(invalidCategoryId);

    }

    @Test
    @DisplayName("Should return popular category list successfully")
    void shouldReturnPopularCategoryListSuccessfully() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        List<Category> categories = List.of(testCategory);
        Page<Category> categoryPage = new PageImpl<>(categories, pageable, 1);

        when(categoryRepository.findPopularCategories(pageable)).thenReturn(categoryPage);

        // When
        Page<Category> result = categoryService.findPopularCategories(pageable);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(testCategory.getName(), result.getContent().get(0).getName());
        assertEquals(1, result.getTotalElements());

        verify(categoryRepository, times(1)).findPopularCategories(pageable);

    }

    @Test
    @DisplayName("Should return empty popular category list when no categories found")
    void shouldReturnEmptyPopularCategoryListWhenNoCategoriesFound() {
        // Given
        Pageable pageable = PageRequest.of(0, 10);
        Page<Category> emptyCategoryPage = new PageImpl<>(List.of(), pageable, 0);

        when(categoryRepository.findPopularCategories(pageable)).thenReturn(emptyCategoryPage);

        // When
        Page<Category> result = categoryService.findPopularCategories(pageable);

        // Then
        assertNotNull(result);
        assertEquals(0, result.getTotalElements());
        assertEquals(0, result.getContent().size());

        verify(categoryRepository, times(1)).findPopularCategories(pageable);
    }


}
