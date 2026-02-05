package com.deveyk.northwind.product.controller;

import com.deveyk.northwind.common.response.ErrorResponse;
import com.deveyk.northwind.product.model.request.SupplierRequest;
import com.deveyk.northwind.product.model.response.SupplierResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "Suppliers",
     description = "Supplier management API endpoints for managing supplier information and their product relationships")
public interface ISupplierController {

    @Operation(
            summary = "Get supplier by ID",
            description = "Retrieves detailed information about a specific supplier",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Supplier found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SupplierResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Supplier not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<SupplierResponse>> get(
            @Parameter(description = "Supplier ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "List all suppliers",
            description = "Retrieves a paginated list of all suppliers sorted by company name",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suppliers retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SupplierResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<SupplierResponse>>> list(
            @Parameter(description = "Pagination parameters (default: page=0, size=10, sort=companyName,asc)")
            Pageable pageable
    );

    @Operation(
            summary = "Create new supplier",
            description = "Creates a new supplier with the provided information",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Supplier created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = SupplierResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid supplier data",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<EntityModel<SupplierResponse>> create(
            @Parameter(description = "Supplier details", required = true)
            @Valid @RequestBody SupplierRequest supplierRequest
    );

    @Operation(
            summary = "Delete supplier",
            description = "Deletes a supplier from the system",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Supplier deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<Void> delete(
            @Parameter(description = "Supplier ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Update supplier",
            description = "Updates an existing supplier's information",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Supplier updated successfully",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    ResponseEntity<EntityModel<SupplierResponse>> update(
            @Parameter(description = "Supplier ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated supplier information", required = true)
            @Valid @RequestBody SupplierRequest supplierRequest
    );

    @Operation(
            summary = "List suppliers by country",
            description = "Retrieves a paginated list of suppliers from a specific country",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Suppliers retrieved successfully",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))
            )
    })
    ResponseEntity<CollectionModel<EntityModel<SupplierResponse>>> listByCountry(
            @Parameter(description = "Country name", required = true, example = "USA")
            @RequestParam String country,
            Pageable pageable
    );

    @Operation(
            summary = "Get supplier's product count",
            description = "Returns the total number of products supplied by a specific supplier",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product count retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Supplier not found",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}/product-count")
    ResponseEntity<Map<String, Long>> getProductCount(
            @Parameter(description = "Supplier ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "List popular suppliers",
            description = "Retrieves a paginated list of suppliers sorted by their product popularity",
            tags = {"Suppliers"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Popular suppliers retrieved successfully",
                    content = @Content(schema = @Schema(implementation = SupplierResponse.class))
            )
    })
    ResponseEntity<CollectionModel<EntityModel<SupplierResponse>>> listPopularSuppliers(Pageable pageable);
}