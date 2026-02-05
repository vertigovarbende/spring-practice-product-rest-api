package com.deveyk.northwind.product.service.impl;

import com.deveyk.northwind.product.exception.CannotContinueProductException;
import com.deveyk.northwind.product.exception.InsufficientStockException;
import com.deveyk.northwind.product.exception.CannotDiscontinueProductException;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.repository.CategoryRepository;
import com.deveyk.northwind.product.repository.ProductRepository;
import com.deveyk.northwind.product.repository.SupplierRepository;
import com.deveyk.northwind.product.service.IProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    @Override
    @Cacheable(value = "products", key = "#id")
    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product save(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getId())
                .orElseThrow(() -> new EntityNotFoundException("Category not found"));
        Supplier supplier = supplierRepository.findById(product.getSupplier().getId())
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

        return productRepository.save(Product.builder()
                        .name(product.getName())
                        .category(category)
                        .supplier(supplier)
                        .sku(product.getSku())
                        .barcode(product.getBarcode())
                        .price(product.getPrice())
                        .quantityPerUnit(product.getQuantityPerUnit())
                        .unitsInStock(product.getUnitsInStock())
                        .build());
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    @CacheEvict(value = "products", allEntries = true)
    public void deleteById(Long id) {
        if (productRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> findByCategoryId(Long categoryId, Pageable pageable) {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    @Override
    public Page<Product> findByCategoryName(String categoryName, Pageable pageable) {
        if (categoryRepository.findByName(categoryName).isEmpty()) {
            throw new EntityNotFoundException("Category not found");
        }
        return productRepository.findByCategoryName(categoryName, pageable);
    }

    @Override
    public Page<Product> findBySupplierId(Long supplierId, Pageable pageable) {
        if (supplierRepository.findById(supplierId).isEmpty()) {
            throw new EntityNotFoundException("Supplier not found");
        }
        return productRepository.findBySupplierId(supplierId, pageable);
    }

    @Override
    public Page<Product> findBySupplierCompanyName(String companyName, Pageable pageable) {
        if (supplierRepository.findByCompanyName(companyName).isEmpty()) {
            throw new EntityNotFoundException("Supplier not found");
        }
        return productRepository.findBySupplierCompanyName(companyName, pageable);
    }

    @Override
    public Page<Product> findLowStock(Pageable pageable) {
        return productRepository.findLowStock(pageable);
    }

    // Domain and Entity???
    // This method doesn't look like a coordinative method????
    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product adjustStock(Long id, Long stockQuantityChange) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));


        Long currentStock = product.getUnitsInStock() != null ? product.getUnitsInStock() : 0L;
        long newStock = currentStock + stockQuantityChange;

        if (newStock < 0) {
            throw new InsufficientStockException("Stock cannot be negative");
        }

        product.setUnitsInStock(newStock);
        return productRepository.save(product);
    }

    @Override
    public Page<Product> findDiscontinued(Pageable pageable) {
        return productRepository.findByDiscontinued(true, pageable);
    }

    // Domain and Entity???
    // This method doesn't look like a coordinative method????
    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product discontinue(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        long currentStock = product.getUnitsInStock() != null ? product.getUnitsInStock() : 0L;
        if (currentStock > 5) {
            throw new CannotDiscontinueProductException("Cannot discontinue a product that still has stock");
        }

        product.setDiscontinued(true);
        return productRepository.save(product);
    }

    // Domain and Entity???
    // This method doesn't look like a coordinative method????
    @Override
    @CacheEvict(value = "products", allEntries = true)
    public Product continueProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        long currentStock = product.getUnitsInStock() != null ? product.getUnitsInStock() : 0L;
        if (currentStock < 5) {
            throw new CannotContinueProductException("Cannot continue a product that has not enough stock");
        }
        product.setDiscontinued(false);
        return productRepository.save(product);
    }
}
