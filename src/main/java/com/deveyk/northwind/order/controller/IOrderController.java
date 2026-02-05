package com.deveyk.northwind.order.controller;

import com.deveyk.northwind.common.response.ErrorResponse;
import com.deveyk.northwind.order.model.request.OrderRequest;
import com.deveyk.northwind.order.model.response.OrderResponse;
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

@Tag(name = "Orders",
     description = "Order management API endpoints for creating, retrieving, updating, and deleting orders")
public interface IOrderController {

    @Operation(
            summary = "Get order by ID",
            description = "Retrieves detailed information about a specific order including order items, customer, and shipping details",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
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
    ResponseEntity<EntityModel<OrderResponse>> get(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "List all orders",
            description = "Retrieves a paginated list of all orders with basic information",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of orders retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<OrderResponse>>> list(
            @Parameter(
                    description = "Pagination parameters (default: page=0, size=10, sort=id,asc)",
                    schema = @Schema(implementation = Pageable.class)
            )
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    );

    @Operation(
            summary = "Create new order",
            description = "Creates a new order with the provided order details, items, and shipping information",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Order created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid order data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<OrderResponse>> create(
            @Parameter(description = "Order details including items and shipping information", required = true)
            @Valid @RequestBody OrderRequest orderRequest
    );

    @Operation(
            summary = "Update existing order",
            description = "Updates an existing order with new information while maintaining the order history",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Order updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid order data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422",
                    description = "Validation error",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<OrderResponse>> update(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated order information", required = true)
            @Valid @RequestBody OrderRequest orderRequest
    );

    @Operation(
            summary = "Delete order",
            description = "Deletes an order from the system (soft delete)",
            tags = {"Orders"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Order deleted successfully"
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
    ResponseEntity<Void> delete(
            @Parameter(description = "Order ID", required = true, example = "1")
            @PathVariable Long id
    );
}