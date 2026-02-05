package com.deveyk.northwind.product.controller.impl;

import com.deveyk.northwind.product.controller.IProductController;
import com.deveyk.northwind.product.mapper.ProductMapper;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.model.response.ProductResponse;
import com.deveyk.northwind.product.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;
    private final ProductMapper productMapper;

    /**
     * Retrieves a product by its ID.
     *
     * @param id the unique identifier of the product to be retrieved
     * @return a ResponseEntity containing the EntityModel of the product response
     */
    // GET - get - /api/products/{id}
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProductResponse>> get(@PathVariable Long id) {
        ProductResponse productResponse = productMapper.toResponse(productService.findById(id));
        return ResponseEntity.ok(toModel(productResponse));
    }

    /**
     * Retrieves a pageable, cached list of products.
     *
     * @param pageable the pagination and sorting information, including page size, sort field, and sort direction
     * @return a ResponseEntity containing a CollectionModel of EntityModels wrapping ProductResponse objects
     */
    // GET - list - /api/products - pageable - cache
    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> list(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findAll(pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).list(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Creates a new product.
     * This operation is allowed for roles 'ADMIN' and 'WAREHOUSE'.
     *
     * @param productRequest the product details to be created, provided in the request body
     * @return a ResponseEntity containing an EntityModel of the created ProductResponse,
     *         including a location header with the URI to access the created product
     */
    // POST - create - /api/products
    @Override
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public ResponseEntity<EntityModel<ProductResponse>> create(@Valid @RequestBody ProductRequest productRequest) {
        Product savedProduct = productService.save(productMapper.toEntity(productRequest));
        ProductResponse productResponse = productMapper.toResponse(savedProduct);
        EntityModel<ProductResponse> entityModelOfProductResponse = this.toModel(productResponse);
        return ResponseEntity.created(linkTo(methodOn(ProductController.class).get(productResponse.getId())).toUri())
                .body(entityModelOfProductResponse);
    }

    /**
     * Updates an existing product with the specified ID using the provided product details.
     *
     * @param id the ID of the product to be updated
     * @param productRequest the product details to update the product with
     * @return a ResponseEntity containing an EntityModel of the updated ProductResponse
     */
    // PUT - update - /api/products/{id} - THERE IS A PROBLEM TO UPDATE EXISTING PRODUCT!
    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public ResponseEntity<EntityModel<ProductResponse>> update(@PathVariable Long id, @Valid @RequestBody ProductRequest productRequest) {
        ProductRequest updateProductRequest = ProductRequest.builder()
                .id(id)
                .name(productRequest.getName())
                .category(productRequest.getCategory())
                .supplier(productRequest.getSupplier())
                .sku(productRequest.getSku())
                .barcode(productRequest.getBarcode())
                .price(productRequest.getPrice())
                .quantityPerUnit(productRequest.getQuantityPerUnit())
                .unitsInStock(productRequest.getUnitsInStock())
                .build();
        Product product = productService.update(productMapper.toEntity(updateProductRequest));
        EntityModel<ProductResponse> entityModelOfProductResponse = this.toModel(productMapper.toResponse(product));
        return ResponseEntity.ok(entityModelOfProductResponse);
    }

    /**
     * Deletes a product identified by its unique ID.
     *
     * @param id the unique identifier of the product to be deleted
     * @return a ResponseEntity with no content if the deletion is successful
     */
    // DELETE - delete - /api/products/{id}
    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves a paginated list of products based on the given category ID.
     *
     * @param categoryId the ID of the category to retrieve products for
     * @param pageable the pagination and sorting information
     * @return a ResponseEntity containing a collection model of entity models for product responses
     */
    // GET - list by category 'id' - /api/products/category/{categoryId} - endpoint name????
    @Override
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listByCategoryId(
            @PathVariable Long categoryId,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findByCategoryId(categoryId, pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).listByCategoryId(categoryId, null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Retrieves a pageable list of products filtered by the provided category name.
     *
     * @param categoryName the name of the category for which products are to be retrieved; must not be null or empty
     * @param pageable an object specifying pagination and sorting information for the results
     * @return a ResponseEntity containing a CollectionModel of EntityModel representations of ProductResponse objects
     */
    // GET - list by category 'name' - /api/products/category?categoryName=category_name
    @Override
    @GetMapping("/category")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listByCategoryName(
            @RequestParam(value = "categoryName", required = true) String categoryName,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findByCategoryName(categoryName, pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).listByCategoryName(categoryName, null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
                );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Retrieves a paginated list of products associated with a specific supplier ID.
     *
     * @param supplierId the unique identifier of the supplier whose products are to be retrieved
     * @param pageable   pagination and sorting information (e.g., page size, sort order)
     * @return a ResponseEntity containing a CollectionModel of EntityModel objects, each representing
     *         a ProductResponse. The response also includes pagination and self-link metadata.
     */
    // GET - list by supplier 'id' - /api/products/supplier/{supplierId}
    @Override
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listBySupplierId(
            @PathVariable Long supplierId,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findBySupplierId(supplierId, pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).listBySupplierId(supplierId, null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Retrieves a list of products associated with a specific supplier's company name.
     *
     * @param companyName the name of the supplier's company to filter products by; must not be null or empty
     * @param pageable the pagination and sorting information for the list of products
     * @return a ResponseEntity containing a CollectionModel of EntityModel wrapping
     *         ProductResponse objects, including relevant links for navigation
     */
    // GET - list by supplier 'companyName' - /api/products/supplier?companyName=ABC
    @Override
    @GetMapping("/supplier")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listBySupplierCompanyName(
            @RequestParam(value = "companyName", required = true) String companyName,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findBySupplierCompanyName(companyName, pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).listBySupplierCompanyName(companyName, null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Retrieves a paginated list of products that are low in stock, sorted based on the number of units in stock.
     *
     * @param pageable an object specifying the pagination and sorting information, including page size, sort order,
     *                 and the property to sort by (default is "unitsInStock" in ascending order).
     * @return a ResponseEntity containing a CollectionModel of EntityModel objects, each representing a product
     *         with low stock, along with related hypermedia links.
     */
    // GET - list low stock - /api/products/low-stock - endpoint name??
    @Override
    @GetMapping("/low-stock")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listLowStock(
            @PageableDefault(size = 10, sort = "unitsInStock", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findLowStock(pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).listLowStock(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Adjusts the stock of a product by a specified quantity change.
     * This operation can be performed only by users with roles 'ADMIN' or 'WAREHOUSE'.
     *
     * @param id The ID of the product whose stock is being adjusted.
     * @param quantityChange The amount by which to increase or decrease the product's stock. A positive value increases the stock, while a negative value decreases it.
     * @return A ResponseEntity containing an EntityModel of the updated ProductResponse object after the stock adjustment.
     */
    // PATCH - adjust stock - /api/products/{id}/stock?quantityChange=10
    @Override
    @PatchMapping("/{id}/stock")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public ResponseEntity<EntityModel<ProductResponse>> adjustStock(@PathVariable Long id,
                                                                    @RequestParam(value = "quantityChange", required = true) Long quantityChange) {
        Product updatedProduct = productService.adjustStock(id, quantityChange);
        ProductResponse productResponse = productMapper.toResponse(updatedProduct);
        EntityModel<ProductResponse> entityModelOfProductResponse = this.toModel(productResponse);
        return ResponseEntity.ok(entityModelOfProductResponse);
    }

    /**
     * Retrieves a paginated and sorted list of discontinued products.
     *
     * @param pageable an object for pagination and sorting information, including the page size, sort property, and sort direction
     * @return a ResponseEntity containing a CollectionModel of EntityModel instances wrapping ProductResponse objects,
     *         representing the discontinued products
     */
    // GET - list discontinued products - /api/products/discontinued
    @Override
    @GetMapping("/discontinued")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listDiscontinued(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductResponse>> productsResponse = productService.findDiscontinued(pageable).stream()
                .map(productMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<ProductResponse>> collectionModelOfProductsResponse = CollectionModel.of(productsResponse,
                linkTo(methodOn(ProductController.class).listDiscontinued(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsResponse);
    }

    /**
     * Discontinues a product by its identifier.
     * This operation is only accessible to users with roles 'ADMIN' or 'WAREHOUSE'.
     *
     * @param id the unique identifier of the product to be discontinued
     * @return a ResponseEntity containing an EntityModel of the discontinued product's response
     */
    // PATCH - discontinue product - /api/products/{id}/discontinue
    @Override
    @PatchMapping("/{id}/discontinue")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public ResponseEntity<EntityModel<ProductResponse>> discontinue(@PathVariable Long id) {
        Product discontinuedProduct = productService.discontinue(id);
        ProductResponse productResponse = productMapper.toResponse(discontinuedProduct);
        EntityModel<ProductResponse> entityModelOfProductResponse = this.toModel(productResponse);
        return ResponseEntity.ok(entityModelOfProductResponse);
    }

    /**
     * Continues a product by the given ID. This operation is accessible to users with the roles
     * 'ADMIN' or 'WAREHOUSE'. The method retrieves the product, updates its status using the
     * service layer, maps the product to a response object, and returns it wrapped in an EntityModel.
     *
     * @param id the unique identifier of the product to be continued
     * @return a ResponseEntity containing an EntityModel of the ProductResponse, representing the
     *         continued product's details
     */
    // PATCH - continue product - /api/products/{id}/continue
    @Override
    @PatchMapping("/{id}/continue")
    @PreAuthorize("hasAnyRole('ADMIN', 'WAREHOUSE')")
    public ResponseEntity<EntityModel<ProductResponse>> continueProduct(@PathVariable Long id) {
        Product continuedProduct = productService.continueProduct(id);
        ProductResponse productResponse = productMapper.toResponse(continuedProduct);
        EntityModel<ProductResponse> entityModelOfProductResponse = this.toModel(productResponse);
        return ResponseEntity.ok(entityModelOfProductResponse);
    }
    
    /**
     * Converts a given ProductResponse object into an EntityModel, enriching it
     * with self-referencing and related resource links.
     *
     * @param product the ProductResponse object to be converted into a model
     * @return an EntityModel of the ProductResponse, including links to itself,
     *         its associated category, supplier, and a list of products
     */
    private EntityModel<ProductResponse> toModel(ProductResponse product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).get(product.getId())).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(ProductController.class).list(null)).withRel("products")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(CategoryController.class).get(product.getCategory().getId())).withRel("category")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(SupplierController.class).get(product.getSupplier().getId())).withRel("supplier")
                        .withType(HttpMethod.GET.name())
        );
    }

}
