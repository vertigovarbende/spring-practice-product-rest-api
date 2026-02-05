package com.deveyk.northwind.integration.product.repository;

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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("ProductRepository Integration Tests")
public class ProductRepositoryIT {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test")
            .withReuse(true);

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.flyway.enabled", () -> "false");
    }

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
        testCategory = Category.builder()
                .id(1L)
                .name("Beverages")
                .description("Soft drinks, coffees, teas, beers, and ales")
                .build();
        testCategory.setCreatedAt(java.time.LocalDateTime.now());
        testCategory.setUpdatedAt(java.time.LocalDateTime.now());

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
        testSupplier.setCreatedAt(java.time.LocalDateTime.now());
        testSupplier.setUpdatedAt(java.time.LocalDateTime.now());

        testCategory = entityManager.persistAndFlush(testCategory);
        testSupplier = entityManager.persistAndFlush(testSupplier);
    }

    private Product createTestProduct(String name, String sku, String barcode) {
        return Product.builder()
                .name(name)
                .supplier(testSupplier)
                .category(testCategory)
                .sku(sku)
                .barcode(barcode)
                .quantityPerUnit("10 boxes x 20 bags")
                .price(BigDecimal.valueOf(18.00))
                .unitsInStock(39L)
                .unitsOnOrder(0L)
                .reorderLevel(10L)
                .discontinued(false)
                .build();
    }

    @Test
    @DisplayName("Should save and find product by id")
    void shouldSaveAndFindProductById() {

        // Given
        Product product = createTestProduct("Test Product", "SP75017", "BAR1000000001");

        // When
        Product savedProduct = productRepository.save(product);
        entityManager.flush();
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // Then
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get().getName()).isEqualTo(product.getName());
        assertThat(foundProduct.get().getSupplier().getCompanyName()).isEqualTo(product.getSupplier().getCompanyName());
        assertThat(foundProduct.get().getCategory().getName()).isEqualTo(product.getCategory().getName());

    }


}
