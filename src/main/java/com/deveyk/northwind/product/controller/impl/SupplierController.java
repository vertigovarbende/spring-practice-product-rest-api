package com.deveyk.northwind.product.controller.impl;

import com.deveyk.northwind.product.controller.ISupplierController;
import com.deveyk.northwind.product.mapper.SupplierMapper;
import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.model.request.SupplierRequest;
import com.deveyk.northwind.product.model.response.SupplierResponse;
import com.deveyk.northwind.product.service.ISupplierService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController implements ISupplierController {

    private final ISupplierService supplierService;
    private final SupplierMapper supplierMapper;

    /**
     * Retrieves a supplier by its unique identifier.
     *
     * @param id the unique identifier of the supplier to retrieve
     * @return a {@link ResponseEntity} containing an {@link EntityModel} of {@link SupplierResponse}
     */
    // GET - get - /api/suppliers/{id}
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SupplierResponse>> get(@PathVariable Long id) {
        SupplierResponse supplierResponse = supplierMapper.toResponse(supplierService.findById(id));
        return ResponseEntity.ok(this.toModel(supplierResponse));
    }

    /**
     * Retrieves a paginated list of suppliers with associated metadata.
     *
     * @param pageable the pagination and sorting information, including page size, sorting field,
     *                 and sort direction
     * @return a {@link ResponseEntity} containing a {@link CollectionModel} of
     *         {@link EntityModel} with {@link SupplierResponse}, along with pagination details
     */
    // GET - list - /api/suppliers - pageable - cache
    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<SupplierResponse>>> list(
            @PageableDefault(size = 10, sort = "companyName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<SupplierResponse>> suppliersResponse = supplierService.findAll(pageable).stream()
                .map(supplierMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<SupplierResponse>> collectionModelOfSuppliersResponse = CollectionModel.of(suppliersResponse,
                linkTo(methodOn(SupplierController.class).list(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
                );
        return ResponseEntity.ok(collectionModelOfSuppliersResponse);
    }

    /**
     * Handles the creation of a new supplier in the system. This method processes a supplier creation
     * request, saves the supplier details, and returns a structured response with the created resource.
     * The method is secured and can only be accessed by users with 'ADMIN' role or both 'MANAGER' and
     * 'WAREHOUSE' roles.
     *
     * @param supplierRequest the request body containing the details of the supplier to be created
     * @return a {@link ResponseEntity} containing an {@link EntityModel} of {@link SupplierResponse}
     *         with the created supplier details, and provides a link to the newly created resource
     */
    // POST - create - /api/suppliers
    @Override
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and hasRole('WAREHOUSE'))")
    public ResponseEntity<EntityModel<SupplierResponse>> create(@Valid @RequestBody SupplierRequest supplierRequest) {
        Supplier savedSupplier = supplierService.save(supplierMapper.toEntity(supplierRequest));
        SupplierResponse supplierResponse = supplierMapper.toResponse(savedSupplier);
        EntityModel<SupplierResponse> entityModelOfSupplierResponse = this.toModel(supplierResponse);
        return ResponseEntity.created(linkTo(methodOn(SupplierController.class).get(supplierResponse.getId())).toUri())
                .body(entityModelOfSupplierResponse);
    }

    /**
     * Deletes a supplier by its unique identifier. This operation is restricted to users
     * with the roles 'ADMIN' or both 'MANAGER' and 'WAREHOUSE'.
     *
     * @param id the unique identifier of the supplier to delete
     * @return a {@link ResponseEntity} with an HTTP status of 204 (No Content) upon successful deletion
     */
    // DELETE - delete - /api/suppliers/{id}
    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and hasRole('WAREHOUSE'))")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the details of an existing supplier. The operation is restricted to users with the
     * roles 'ADMIN' or both 'MANAGER' and 'WAREHOUSE'. The update requires the unique identifier
     * of the supplier and the fully populated request body with the new supplier details.
     *
     * @param id              the unique identifier of the supplier to update
     * @param supplierRequest the request body containing the updated supplier details
     * @return a {@link ResponseEntity} containing an {@link EntityModel} of {@link SupplierResponse}
     *         with the updated supplier details
     */
    // PUT - update - /api/suppliers/{id} - THERE IS A PROBLEM TO UPDATE EXISTING SUPPLIER!
    @Override
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('MANAGER') and hasRole('WAREHOUSE'))")
    public ResponseEntity<EntityModel<SupplierResponse>> update(@PathVariable Long id,
                                                                @Valid @RequestBody SupplierRequest supplierRequest) {
        Supplier supplier = supplierService.update(supplierMapper.toEntity(supplierRequest));
        EntityModel<SupplierResponse> entityModelOfSupplierResponse = this.toModel(supplierMapper.toResponse(supplier));
        return ResponseEntity.ok(entityModelOfSupplierResponse);
    }

    /**
     * Retrieves a collection of suppliers filtered by country.
     *
     * @param country the name of the country to filter suppliers by
     * @param pageable the pagination and sorting information
     * @return a ResponseEntity containing a CollectionModel of suppliers, with each supplier represented as an EntityModel
     */
    // GET - list by country - /api/suppliers/country?country=country_name
    @Override
    @GetMapping("/country")
    public ResponseEntity<CollectionModel<EntityModel<SupplierResponse>>> listByCountry(
            @RequestParam(value = "country", required = true) String country,
            @PageableDefault(size = 10, sort = "companyName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<SupplierResponse>> suppliersResponse = supplierService.findByCountry(country, pageable).stream()
                .map(supplierMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<SupplierResponse>> collectionModelOfSuppliersResponse = CollectionModel.of(suppliersResponse,
                linkTo(methodOn(SupplierController.class).listByCountry(country, null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfSuppliersResponse);
    }

    /**
     * Retrieves the number of products associated with a specific supplier.
     *
     * @param id the unique identifier of the supplier whose product count is to be retrieved
     * @return a ResponseEntity containing a map with the product count of the specified supplier
     */
    // GET - get product count of supplier - /api/suppliers/{id}/product-count
    @Override
    @GetMapping("/{id}/product-count")
    public ResponseEntity<Map<String, Long>> getProductCount(@PathVariable Long id) {
        long productCount = supplierService.getProductCountBySupplierId(id);
        Map<String, Long> response = new HashMap<>();
        response.put("productCount", productCount);
        return ResponseEntity.ok(response);
    }

    /**
     * Retrieves a paginated list of popular suppliers sorted by company name in ascending order.
     *
     * @param pageable the pagination information, including page size, sorting details, and page number
     * @return a {@code ResponseEntity} containing a {@code CollectionModel} of {@code EntityModel} representing popular suppliers,
     *         along with links for hypermedia navigation
     */
    // GET - list popular suppliers - /api/suppliers/popular
    @Override
    @GetMapping("/popular")
    public ResponseEntity<CollectionModel<EntityModel<SupplierResponse>>> listPopularSuppliers(
            @PageableDefault(size = 10, sort = "companyName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<SupplierResponse>> suppliersResponse = supplierService.findPopularSuppliers(pageable).stream()
                .map(supplierMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<SupplierResponse>> collectionModelOfSuppliersResponse = CollectionModel.of(suppliersResponse,
                linkTo(methodOn(SupplierController.class).listPopularSuppliers(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfSuppliersResponse);
    }

    /**
     * Converts a SupplierResponse object into an EntityModel with relevant links attached.
     *
     * @param supplierResponse the SupplierResponse object to be converted into an EntityModel
     * @return an EntityModel representing the SupplierResponse with self and related resource links
     */
    private EntityModel<SupplierResponse> toModel(SupplierResponse supplierResponse) {
        return EntityModel.of(supplierResponse,
                linkTo(methodOn(SupplierController.class).get(supplierResponse.getId())).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(SupplierController.class).list(null)).withRel("suppliers")
                        .withType(HttpMethod.GET.name())
                );
    }
}
