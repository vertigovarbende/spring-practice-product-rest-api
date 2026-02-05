package com.deveyk.northwind.customer.controller;

import com.deveyk.northwind.common.response.ErrorResponse;
import com.deveyk.northwind.customer.model.response.CustomerOrderResponse;
import com.deveyk.northwind.customer.model.response.CustomerResponse;
import com.deveyk.northwind.customer.model.response.ProductInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Customer",
     description = "Customer API endpoints for managing customer information, orders and product access")
public interface ICustomerController {

    @Operation(
            summary = "Get customer profile information",
            description = "Retrieves the authenticated customer's profile information",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer information retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized - Authentication required",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden - Insufficient permissions",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<CustomerResponse>> getInfo(
            @Parameter(description = "Spring Security Authentication object", hidden = true)
            Authentication authentication
    );

    @Operation(
            summary = "Get customer by ID",
            description = "Retrieves a specific customer's information by their ID (Admin/Sales only)",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Customer found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Customer not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<CustomerResponse>> getCustomer(
            @Parameter(description = "Customer ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Get all customers",
            description = "Retrieves a paginated list of all customers (Admin/Sales only)",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of customers retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<CustomerResponse>>> getAllCustomers(
            @Parameter(description = "Pagination parameters")
            Pageable pageable
    );

    @Operation(
            summary = "Get all products",
            description = "Retrieves a paginated list of available products for customers",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of products retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductInfoResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<ProductInfoResponse>>> getAllProducts(
            @Parameter(description = "Pagination parameters")
            Pageable pageable
    );

    @Operation(
            summary = "Get product by ID",
            description = "Retrieves detailed information about a specific product",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductInfoResponse.class)
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
    ResponseEntity<EntityModel<ProductInfoResponse>> getProduct(
            @Parameter(description = "Product ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "Get order by ID",
            description = "Retrieves detailed information about a specific order",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrderResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<CustomerOrderResponse>> getOrder(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Spring Security Authentication object", hidden = true)
            Authentication authentication
    );

    @Operation(
            summary = "Get all customer orders",
            description = "Retrieves a paginated list of orders for the authenticated customer",
            tags = {"Customer"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of orders retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomerOrderResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<CustomerOrderResponse>>> getAllOrders(
            @Parameter(description = "Pagination parameters")
            Pageable pageable,
            @Parameter(description = "Spring Security Authentication object", hidden = true)
            Authentication authentication
    );
}