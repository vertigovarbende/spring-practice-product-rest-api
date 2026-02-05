package com.deveyk.northwind.product.controller;

import com.deveyk.northwind.common.response.ErrorResponse;
import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.model.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Products",
     description = "Product management API endpoints for managing product inventory, categories, suppliers, and stock levels")
public interface IProductController {

    @Operation(
            summary = "Get product by ID",
            description = "Retrieves detailed information about a specific product including category and supplier details",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<ProductResponse>> get(@PathVariable Long id);

    @Operation(
            summary = "List all products",
            description = "Retrieves a paginated list of all active products",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> list(
            @Parameter(description = "Pagination parameters (default: page=0, size=10, sort=name,asc)")
            Pageable pageable
    );

    @Operation(
            summary = "Create new product",
            description = "Creates a new product with the provided details",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Product created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid product data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<EntityModel<ProductResponse>> create(
            @Parameter(description = "Product details", required = true)
            @Valid @RequestBody ProductRequest productRequest
    );

    @Operation(
            summary = "Update product",
            description = "Updates an existing product's information",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product updated successfully",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<EntityModel<ProductResponse>> update(
            @Parameter(description = "Product ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated product information", required = true)
            @Valid @RequestBody ProductRequest productRequest
    );

    @Operation(
            summary = "Delete product",
            description = "Deletes a product from the system",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(
            summary = "List products by category ID",
            description = "Retrieves a paginated list of products in a specific category",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products retrieved successfully",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            )
    })
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listByCategoryId(
            @Parameter(description = "Category ID", required = true, example = "1")
            @PathVariable Long categoryId,
            @Parameter(description = "Pagination parameters")
            Pageable pageable
    );

    @Operation(
            summary = "List products by category name",
            description = "Retrieves a paginated list of products in a category specified by name",
            tags = {"Products"}
    )
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listByCategoryName(
            @Parameter(description = "Category name", required = true, example = "Beverages")
            @RequestParam String categoryName,
            Pageable pageable
    );

    @Operation(
            summary = "List products by supplier ID",
            description = "Retrieves a paginated list of products from a specific supplier",
            tags = {"Products"}
    )
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listBySupplierId(
            @Parameter(description = "Supplier ID", required = true, example = "1")
            @PathVariable Long supplierId,
            Pageable pageable
    );

    @Operation(
            summary = "List products by supplier company name",
            description = "Retrieves a paginated list of products from a supplier specified by company name",
            tags = {"Products"}
    )
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listBySupplierCompanyName(
            @Parameter(description = "Supplier company name", required = true, example = "Exotic Liquids")
            @RequestParam String companyName,
            Pageable pageable
    );

    @Operation(
            summary = "List low stock products",
            description = "Retrieves a paginated list of products with stock levels below reorder point",
            tags = {"Products"}
    )
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listLowStock(Pageable pageable);

    @Operation(
            summary = "Adjust product stock",
            description = "Updates the stock level of a product (positive for increase, negative for decrease)",
            tags = {"Products"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Stock adjusted successfully",
                    content = @Content(schema = @Schema(implementation = ProductResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<EntityModel<ProductResponse>> adjustStock(
            @Parameter(description = "Product ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Quantity change (positive or negative)", required = true, example = "10")
            @RequestParam Long quantityChange
    );

    @Operation(
            summary = "List discontinued products",
            description = "Retrieves a paginated list of discontinued products",
            tags = {"Products"}
    )
    ResponseEntity<CollectionModel<EntityModel<ProductResponse>>> listDiscontinued(Pageable pageable);

    @Operation(
            summary = "Discontinue product",
            description = "Marks a product as discontinued",
            tags = {"Products"}
    )
    ResponseEntity<EntityModel<ProductResponse>> discontinue(
            @Parameter(description = "Product ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Continue product",
            description = "Reactivates a discontinued product",
            tags = {"Products"}
    )
    ResponseEntity<EntityModel<ProductResponse>> continueProduct(
            @Parameter(description = "Product ID", required = true, example = "1")
            @PathVariable Long id
    );
}