package com.deveyk.northwind.integration.product.repository.impl;

import com.deveyk.northwind.integration.product.TestDataBuilder;
import com.deveyk.northwind.integration.product.repository.BaseRepositoryIT;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.CategoryRepository;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.repository.SupplierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ProductRepository Integration Tests")
public class ProductRepositoryIT extends BaseRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private Category testCategory;

    private Supplier testSupplier;

    @BeforeEach
    void setUp() {
        testCategory = TestDataBuilder.createTestCategory();
        testSupplier = TestDataBuilder.createTestSupplier();

        testCategory = entityManager.persistAndFlush(testCategory);
        testSupplier = entityManager.persistAndFlush(testSupplier);
    }

    @Test
    @DisplayName("Should save and find product by id")
    void shouldSaveAndFindProductById() {

        // Given
        Product product = TestDataBuilder.createTestProduct("Test Product", "SP75017", "BAR1000000001");
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());

        // When
        Product savedProduct = productRepository.save(product);
        entityManager.flush(); // to flush cache
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // Then
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo(product.getName());
        assertThat(foundProduct.get().getSupplier().getCompanyName()).isEqualTo(product.getSupplier().getCompanyName());
        assertThat(foundProduct.get().getCategory().getName()).isEqualTo(product.getCategory().getName());

    }

    @Test
    @DisplayName("Should find all products with pagination")
    void shouldFindAllProductsWithPagination() {

        // Given
        for (int i = 1; i <= 5; ++i) {
            Product product = TestDataBuilder.createTestProduct("Test Product " + i, "SP75017" + i, "BAR100000000" + i);
            entityManager.persistAndFlush(product);
        }
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("name").ascending());

        // When
        Page<Product> productPage = productRepository.findAll(pageRequest);

        // Then
        assertNotNull(productPage);
        assertEquals(3, productPage.getContent().size());
        assertEquals(5, productPage.getTotalElements());
        assertEquals(2, productPage.getTotalPages());
        assertEquals("Test Product 1", productPage.getContent().get(0).getName());

    }

    @Test
    @DisplayName("Should find all products with pagination by category ID")
    void shouldFindAllProductsWithPaginationByCategoryId() {

        // Given
        for (int i = 1; i <= 5; i++) {
            Product product = TestDataBuilder.createTestProduct("Category Product " + i, "SP75017" + i, "BAR100000000" + i);
            product.setCategory(testCategory);
            product.setCreatedAt(java.time.LocalDateTime.now());
            product.setUpdatedAt(java.time.LocalDateTime.now());
            entityManager.persistAndFlush(product);
        }

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("name").ascending());

        // When
        Page<Product> productPage = productRepository.findByCategoryId(testCategory.getId(), pageRequest);

        // Then
        assertNotNull(productPage);
        assertEquals(3, productPage.getContent().size());
        assertEquals(5, productPage.getTotalElements());
        assertEquals(2, productPage.getTotalPages());
        assertEquals("Category Product 1", productPage.getContent().get(0).getName());
    }

    @Test
    @DisplayName("Should find all products with pagination by category NAME")
    void shouldFindAllProductsWithPaginationByCategoryName() {

        // Given
        for (int i = 1; i <= 5; i++) {
            Product product =  TestDataBuilder.createTestProduct("Category Product " + i, "SP75017" + i, "BAR100000000" + i);
            product.setCategory(testCategory);
            product.setCreatedAt(java.time.LocalDateTime.now());
            product.setUpdatedAt(java.time.LocalDateTime.now());
            entityManager.persistAndFlush(product);
        }

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("name").ascending());

        // When
        Page<Product> productPage = productRepository.findByCategoryName(testCategory.getName(), pageRequest);

        // Then
        assertNotNull(productPage);
        assertEquals(3, productPage.getContent().size());
        assertEquals(5, productPage.getTotalElements());
        assertEquals(2, productPage.getTotalPages());
        assertEquals("Category Product 1", productPage.getContent().get(0).getName());

    }

    @Test
    @DisplayName("Should find all products with pagination by supplier ID")
    void shouldFindAllProductsWithPaginationBySupplierId() {

        // Given
        for (int i = 1; i <= 5; i++) {
            Product product =  TestDataBuilder.createTestProduct("Supplier Product " + i, "SP75017" + i, "BAR100000000" + i);
            product.setSupplier(testSupplier);
            product.setCreatedAt(java.time.LocalDateTime.now());
            product.setUpdatedAt(java.time.LocalDateTime.now());
            entityManager.persistAndFlush(product);
        }

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("name").ascending());

        // When
        Page<Product> productPage = productRepository.findBySupplierId(testSupplier.getId(), pageRequest);

        // Then
        assertNotNull(productPage);
        assertEquals(3, productPage.getContent().size());
        assertEquals(5, productPage.getTotalElements());
        assertEquals(2, productPage.getTotalPages());
        assertEquals("Supplier Product 1", productPage.getContent().get(0).getName());


    }

    @Test
    @DisplayName("Should find all products with pagination by supplier NAME")
    void shouldFindAllProductsWithPaginationBySupplierName() {

        // Given
        for (int i = 1; i <= 5; i++) {
            Product product =  TestDataBuilder.createTestProduct("Supplier Product " + i, "SP75017" + i, "BAR100000000" + i);
            product.setSupplier(testSupplier);
            product.setCreatedAt(java.time.LocalDateTime.now());
            product.setUpdatedAt(java.time.LocalDateTime.now());
            entityManager.persistAndFlush(product);
        }

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by("name").ascending());

        // When
        Page<Product> productPage = productRepository.findBySupplierCompanyName(testSupplier.getCompanyName(), pageRequest);

        // Then
        assertNotNull(productPage);
        assertEquals(3, productPage.getContent().size());
        assertEquals(5, productPage.getTotalElements());
        assertEquals(2, productPage.getTotalPages());
        assertEquals("Supplier Product 1", productPage.getContent().get(0).getName());

    }

    @Test
    @DisplayName("Should delete product by id")
    void shouldDeleteProductById() {
        // Given
        Product product =  TestDataBuilder.createTestProduct("Test Product", "SP75017", "BAR1000000001");
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());
        Product savedProduct = entityManager.persistAndFlush(product);

        // When
        productRepository.deleteById(savedProduct.getId());
        entityManager.flush();

        // Then
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());
        assertThat(foundProduct).isNotPresent();
        assertThat(foundProduct).isEmpty();
    }

    @Test
    @DisplayName("Should count products correctly")
    void shouldCountProductsCorrectly() {

        // Given
        for (int i = 1; i <= 3; ++i) {
            Product product =  TestDataBuilder.createTestProduct("Test Product " + i, "SP75017" + i, "BAR100000000" + i);
            entityManager.persistAndFlush(product);
        }

        // When
        long count = productRepository.count();

        // Then
        assertEquals(3, count);
    }

    @Test
    @DisplayName("Should update product by id")
    void shouldUpdateProductById() {

        // Given
        Product product =  TestDataBuilder.createTestProduct("Test Product", "SP75017", "BAR1000000001");
        product.setCreatedAt(java.time.LocalDateTime.now());
        product.setUpdatedAt(java.time.LocalDateTime.now());
        Product savedProduct = entityManager.persistAndFlush(product);

        // When
        savedProduct.setName("Updated Test Product");
        savedProduct.setSupplier(testSupplier);
        savedProduct.setCategory(testCategory);
        savedProduct.setSku("SP75017-UPDATED");
        savedProduct.setBarcode("BAR1000000002");
        savedProduct.setQuantityPerUnit("10 boxes x 20 bags (UPDATED)");
        savedProduct.setPrice(BigDecimal.valueOf(19.00));
        savedProduct.setUnitsInStock(40L);
        savedProduct.setUnitsOnOrder(1L);

        // Then
        Product updatedProduct = productRepository.save(savedProduct);
        entityManager.flush();
        assertThat(updatedProduct.getName()).isEqualTo("Updated Test Product");
        assertThat(updatedProduct.getSupplier().getCompanyName()).isEqualTo(testSupplier.getCompanyName());
        assertThat(updatedProduct.getCategory().getName()).isEqualTo(testCategory.getName());
    }

    @Test
    @DisplayName("Should check if product exists by id")
    void shouldCheckIfProductExistsById() {

        // Given
        Product product =  TestDataBuilder.createTestProduct("Test Product", "SP75017", "BAR1000000001");
        Product savedProduct = entityManager.persistAndFlush(product);

        // When/Then
        assertThat(productRepository.existsById(savedProduct.getId())).isTrue();
        assertThat(productRepository.existsById(9999L)).isFalse();

    }

    @Test
    @DisplayName("Should find products by category ID")
    void shouldFindProductsByCategoryId() {

        // Given
        Category beveragesCategory = testCategory;
        Category bookCategory = Category.builder()
                .name("Book")
                .description("Hardcover or softcover books")
                .build();
        bookCategory.setCreatedAt(java.time.LocalDateTime.now());
        bookCategory.setUpdatedAt(java.time.LocalDateTime.now());
        bookCategory = entityManager.persistAndFlush(bookCategory);

        // Create products in different categories
        Product beveragesProduct1 =  TestDataBuilder.createTestProduct("Beverages Product 1", "SP75017", "BAR1000000001");
        beveragesProduct1.setCategory(beveragesCategory);
        beveragesProduct1.setCreatedAt(java.time.LocalDateTime.now());
        beveragesProduct1.setUpdatedAt(java.time.LocalDateTime.now());

        Product beveragesProduct2 =  TestDataBuilder.createTestProduct("Beverages Product 2", "SP75018", "BAR1000000002");
        beveragesProduct2.setCategory(beveragesCategory);
        beveragesProduct2.setCreatedAt(java.time.LocalDateTime.now());
        beveragesProduct2.setUpdatedAt(java.time.LocalDateTime.now());

        Product bookProduct =  TestDataBuilder.createTestProduct("Book Product", "SP75019", "BAR1000000003");
        bookProduct.setCategory(bookCategory);
        bookProduct.setCreatedAt(java.time.LocalDateTime.now());
        bookProduct.setUpdatedAt(java.time.LocalDateTime.now());

        entityManager.persistAndFlush(beveragesProduct1);
        entityManager.persistAndFlush(beveragesProduct2);
        entityManager.persistAndFlush(bookProduct);

        // When
        List<Product> allProducts = productRepository.findAll();

        /*  - name
        long beveragesCount = allProducts.stream()
                .filter(p -> "Beverages".equals(p.getCategory().getName()))
                .count();
        long booksCount = allProducts.stream()
                .filter(p -> "Books".equals(p.getCategory().getName()))
                .count();
        */

        /*
        long beveragesCount = allProducts.stream()
                .filter(p -> beveragesCategory.getId().equals(p.getCategory().getId()))
                .count();
        Category finalBookCategory = bookCategory; // to effective final?
        long booksCount = allProducts.stream()
                .filter(p -> finalBookCategory.getId().equals(p.getCategory().getId()))
                .count();
        */

        long beveragesCount = productRepository.countByCategoryId(beveragesCategory.getId());
        long booksCount = productRepository.countByCategoryId(bookCategory.getId());

        // Then
        assertEquals(2, beveragesCount);
        assertEquals(1, booksCount);


    }

    @Test
    @DisplayName("Should find products by supplier ID")
    void shouldFindProductsBySupplierId() {

        // Given
        Supplier exoticLiquids = testSupplier;
        Supplier anotherSupplier = Supplier.builder()
                .companyName("Another Supplier")
                .contactName("Chars")
                .contactTitle("Sales Manager")
                .address("asdasda")
                .city("asdasd")
                .region(null)
                .postalCode("asd")
                .country("TR")
                .phone("1234567890")
                .fax(null)
                .homepage(null)
                .build();
        anotherSupplier.setCreatedAt(java.time.LocalDateTime.now());
        anotherSupplier.setUpdatedAt(java.time.LocalDateTime.now());
        anotherSupplier = entityManager.persistAndFlush(anotherSupplier);

        // Create products in different suppliers
        Product exoticLiquidsProduct1 = TestDataBuilder.createTestProduct("Exotic Liquids Product 1", "SP75017", "BAR1000000001");
        exoticLiquidsProduct1.setSupplier(exoticLiquids);
        exoticLiquidsProduct1.setCreatedAt(java.time.LocalDateTime.now());
        exoticLiquidsProduct1.setUpdatedAt(java.time.LocalDateTime.now());

        Product exoticLiquidsProduct2 = TestDataBuilder.createTestProduct("Exotic Liquids Product 2", "SP75018", "BAR1000000002");
        exoticLiquidsProduct2.setSupplier(exoticLiquids);
        exoticLiquidsProduct2.setCreatedAt(java.time.LocalDateTime.now());
        exoticLiquidsProduct2.setUpdatedAt(java.time.LocalDateTime.now());

        Product anotherSupplierProduct = TestDataBuilder.createTestProduct("Another Supplier Product", "SP75019", "BAR1000000003");
        anotherSupplierProduct.setSupplier(anotherSupplier);
        anotherSupplierProduct.setCreatedAt(java.time.LocalDateTime.now());
        anotherSupplierProduct.setUpdatedAt(java.time.LocalDateTime.now());

        entityManager.persistAndFlush(exoticLiquidsProduct1);
        entityManager.persistAndFlush(exoticLiquidsProduct2);
        entityManager.persistAndFlush(anotherSupplierProduct);

        // When
        List<Product> allProducts = productRepository.findAll();

        // name
        /*
        long exoticLiquidsCount = allProducts.stream()
                .filter(p -> "Exotic Liquids".equals(p.getSupplier().getCompanyName()))
                .count();
        long anotherSupplierCount = allProducts.stream()
                .filter(p -> "Another Supplier".equals(p.getSupplier().getCompanyName()))
                .count();
        */

        // id
        /*
        long exoticLiquidsCount = allProducts.stream()
                .filter(p -> exoticLiquids.getId().equals(p.getSupplier().getId()))
                .count();
        long anotherSupplierCount = allProducts.stream()
                .filter(p -> anotherSupplierProduct.getId().equals(p.getSupplier().getId()))
                .count();
        */

        long exoticLiquidsCount = productRepository.countBySupplierId(exoticLiquids.getId());
        long anotherSupplierCount = productRepository.countBySupplierId(anotherSupplier.getId());

        // Then
        assertEquals(2, exoticLiquidsCount);
        assertEquals(1, anotherSupplierCount);

    }


}
