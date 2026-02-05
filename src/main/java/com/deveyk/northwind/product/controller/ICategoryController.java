package com.deveyk.northwind.product.controller;

import com.deveyk.northwind.product.model.request.CategoryRequest;
import com.deveyk.northwind.product.model.response.CategoryResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

import java.util.Map;

@Tag(name = "Categories", description = "REST API for category management")
public interface ICategoryController {

    @Operation(summary = "Get category", description = "Retrieves a category by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category found successfully",
            content = @Content(schema = @Schema(implementation = CategoryResponse.class))),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    ResponseEntity<EntityModel<CategoryResponse>> get(
        @Parameter(description = "ID of the category", required = true) @PathVariable Long id);



    @Operation(summary = "List all categories", description = "Returns a paginated list of categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Categories listed successfully")
    })
    ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> list(
        @Parameter(description = "Pagination information") 
        @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable);


    @Operation(summary = "Create new category", description = "Creates a new category")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Category created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access")
    })
    ResponseEntity<EntityModel<CategoryResponse>> create(
        @Parameter(description = "Category details", required = true) 
        @Valid @RequestBody CategoryRequest categoryRequest);


    @Operation(summary = "Delete category", description = "Deletes a category by its ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Category deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access")
    })
    ResponseEntity<Void> delete(
        @Parameter(description = "ID of the category to delete", required = true) 
        @PathVariable Long id);


    @Operation(summary = "Update category", description = "Updates an existing category by its ID")
    @SecurityRequirement(name = "bearerAuth")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Category updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid request"),
        @ApiResponse(responseCode = "404", description = "Category not found"),
        @ApiResponse(responseCode = "403", description = "Unauthorized access")
    })
    ResponseEntity<EntityModel<CategoryResponse>> update(
        @Parameter(description = "ID of the category to update", required = true) @PathVariable Long id,
        @Parameter(description = "Updated category details", required = true) 
        @Valid @RequestBody CategoryRequest categoryRequest);



    @Operation(summary = "Get product count", 
              description = "Returns the number of products in a specific category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Product count retrieved successfully"),
        @ApiResponse(responseCode = "404", description = "Category not found")
    })
    ResponseEntity<Map<String, Long>> getProductCount(
        @Parameter(description = "ID of the category", required = true) @PathVariable Long id);



    @Operation(summary = "List popular categories", 
              description = "Returns a paginated list of popular categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Popular categories listed successfully")
    })
    ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> listPopularCategories(
        @Parameter(description = "Pagination information") 
        @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable);


}