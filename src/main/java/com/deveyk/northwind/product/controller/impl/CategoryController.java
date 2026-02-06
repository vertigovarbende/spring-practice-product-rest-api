package com.deveyk.northwind.product.controller.impl;

import com.deveyk.northwind.product.controller.ICategoryController;
import com.deveyk.northwind.product.mapper.CategoryMapper;
import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.request.CategoryRequest;
import com.deveyk.northwind.product.model.response.CategoryResponse;
import com.deveyk.northwind.product.service.ICategoryService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController implements ICategoryController {

    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    /**
     * Retrieves a single category by its identifier.
     *
     * @param id the unique identifier of the category to retrieve
     * @return a {@code ResponseEntity} containing the {@code EntityModel} of the {@code CategoryResponse}
     */
    // GET - get - /api/categories/{id}
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoryResponse>> get(@PathVariable Long id) {
        CategoryResponse categoryResponse = categoryMapper.toResponse(categoryService.findById(id));
        return ResponseEntity.ok(this.toModel(categoryResponse));
    }

    /**
     * Retrieves a paginated list of category responses.
     *
     * @param pageable the pagination and sorting information
     * @return a {@code ResponseEntity} containing a {@code CollectionModel} of {@code EntityModel} objects,
     * each representing a {@code CategoryResponse}
     */
    // GET - list - /api/categories - pageable - cache
    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> list(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<CategoryResponse>> categoriesResponse = categoryService.findAll(pageable).stream()
                .map(categoryMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<CategoryResponse>> collectionModelOfCategoriesResponse = CollectionModel.of(categoriesResponse,
                linkTo(methodOn(CategoryController.class).get(null)).withSelfRel()
                        .withType(HttpMethod.GET.toString())
                );
        return ResponseEntity.ok(collectionModelOfCategoriesResponse);
    }

    /**
     * Creates a new category using the provided category request data.
     *
     * @param categoryRequest the {@code CategoryRequest} object containing the details
     *                        required to create a new category
     * @return a {@code ResponseEntity} containing the {@code EntityModel} of the
     *         {@code CategoryResponse} for the newly created category
     */
    // POST - create - /api/categories
    @Override
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and hasRole('WAREHOUSE'))")
    public ResponseEntity<EntityModel<CategoryResponse>> create(@Valid @RequestBody CategoryRequest categoryRequest) {
        Category savedCategory = categoryService.save(categoryMapper.toEntity(categoryRequest));
        CategoryResponse categoryResponse = categoryMapper.toResponse(savedCategory);
        EntityModel<CategoryResponse> entityModelOfCategoryResponse = this.toModel(categoryResponse);
        return ResponseEntity.created(linkTo(methodOn(CategoryController.class).get(categoryResponse.getId())).toUri())
                .body(entityModelOfCategoryResponse);
    }

    /**
     * Deletes a category identified by its unique ID.
     *
     * @param id the unique identifier of the category to be deleted
     * @return a {@code ResponseEntity} with no content if the deletion is successful
     */
    // DELETE - delete - /api/categories/{id}
    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and hasRole('WAREHOUSE'))")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates an existing category identified by its unique ID using the provided
     * category request data.
     *
     * @param id              the unique identifier of the category to be updated
     * @param categoryRequest the {@code CategoryRequest} object containing the updated
     *                        details of the category
     * @return a {@code ResponseEntity} containing the {@code EntityModel} of the
     *         updated {@code CategoryResponse}
     */
    // PUT - update - /api/categories/{id} - - THERE IS A PROBLEM TO UPDATE EXISTING CATEGORY!
    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and hasRole('WAREHOUSE'))")
    public ResponseEntity<EntityModel<CategoryResponse>> update(@PathVariable Long id,
                                                                @Valid @RequestBody CategoryRequest categoryRequest) {
        CategoryRequest updateCategoryRequest = CategoryRequest.builder()
                .id(id)
                .name(categoryRequest.getName())
                .description(categoryRequest.getDescription())
                .build();
        Category category = categoryService.update(categoryMapper.toEntity(updateCategoryRequest));
        EntityModel<CategoryResponse> categoryResponseEntityModel = this.toModel(categoryMapper.toResponse(category));
        return ResponseEntity.ok(categoryResponseEntityModel);
    }

    /**
     * Retrieves the count of products associated with a specific category.
     *
     * @param id the unique identifier of the category
     * @return a {@code ResponseEntity} containing a map with a single entry where the key is "productCount"
     *         and the value is the count of products in the specified category
     */
    // GET - get product count - /api/categories/{id}/product-count - endpoint name???
    @Override
    @GetMapping("/{id}/product-count")
    public ResponseEntity<Map<String, Long>> getProductCount(@PathVariable Long id) {
        long productCount = categoryService.getProductCountByCategoryId(id);
        Map<String, Long> response = new HashMap<>();
        response.put("productCount", productCount);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a paginated list of popular categories.
     *
     * @param pageable the pagination and sorting information for the category list
     * @return a {@code ResponseEntity} containing a {@code CollectionModel} of {@code EntityModel} objects,
     *         each encapsulating a {@code CategoryResponse} for a popular category
     */
    // GET - list popular categories - /api/categories/popular
    @Override
    @GetMapping("/popular")
    public ResponseEntity<CollectionModel<EntityModel<CategoryResponse>>> listPopularCategories(
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<CategoryResponse>> categoriesResponse = categoryService.findPopularCategories(pageable).stream()
                .map(categoryMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<CategoryResponse>> collectionModelOfCategoriesResponse = CollectionModel.of(categoriesResponse,
                linkTo(methodOn(CategoryController.class).listPopularCategories(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfCategoriesResponse);
    }

    /**
     * Converts a {@code CategoryResponse} to an {@code EntityModel} that includes HATEOAS links for the resource.
     *
     * @param categoryResponse the {@code CategoryResponse} object representing the category details
     * @return an {@code EntityModel} of {@code CategoryResponse} that includes HATEOAS links,
     *         including a self-reference link and a link to the list of categories
     */
    private EntityModel<CategoryResponse> toModel(CategoryResponse categoryResponse) {
        return EntityModel.of(categoryResponse,
                linkTo(methodOn(CategoryController.class).get(categoryResponse.getId())).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(CategoryController.class).list(null)).withRel("categories")
                        .withType(HttpMethod.GET.name())
                );
    }
}
