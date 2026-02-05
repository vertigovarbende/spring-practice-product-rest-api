package com.deveyk.northwind.order.controller.impl;

import com.deveyk.northwind.order.controller.IOrderController;
import com.deveyk.northwind.order.mapper.OrderMapper;
import com.deveyk.northwind.order.model.entity.Order;
import com.deveyk.northwind.order.model.request.OrderRequest;
import com.deveyk.northwind.order.model.response.OrderResponse;
import com.deveyk.northwind.order.service.IOrderService;
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
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController implements IOrderController {

    private final IOrderService orderService;
    private final OrderMapper orderMapper;

    /**
     * Retrieves the details of an order based on the provided order ID.
     *
     * @param id the unique identifier of the order to be retrieved
     * @return a {@code ResponseEntity} containing an {@code EntityModel} of {@code OrderResponse},
     *         which includes the details of the specified order
     */
    // GET - get - /api/orders/{id}
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderResponse>> get(@PathVariable Long id) {
        OrderResponse orderResponse = orderMapper.toResponse(orderService.findById(id));
        return ResponseEntity.ok(toModel(orderResponse));
    }

    /**
     * Retrieves a paginated and optionally sorted list of orders.
     *
     * @param pageable the pagination and sorting information, including page size, sort field, and sort direction.
     * @return a {@code ResponseEntity} containing a {@code CollectionModel} of {@code EntityModel<OrderResponse>},
     *         which includes the list of orders and associated metadata.
     */
    // GET - list - /api/orders - pageable - cache
    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<OrderResponse>>> list(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<OrderResponse>> ordersResponse = this.orderService.findAll(pageable).stream()
                .map(orderMapper::toResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<OrderResponse>> collectionModelOfOrdersResponse = CollectionModel.of(ordersResponse,
                linkTo(methodOn(OrderController.class).list(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfOrdersResponse);
    }

    /**
     * Creates a new order based on the provided order request.
     *
     * @param orderRequest the request object containing the details of the order to be created
     * @return a {@code ResponseEntity} containing an {@code EntityModel} of {@code OrderResponse},
     *         which includes the details of the newly created order
     */
    // POST - create - /api/orders
    @Override
    @PostMapping
    public ResponseEntity<EntityModel<OrderResponse>> create(@Valid @RequestBody OrderRequest orderRequest) {
        Order savedOrder = orderService.save(orderMapper.toEntity(orderRequest));
        OrderResponse orderResponse = orderMapper.toResponse(savedOrder);
        EntityModel<OrderResponse> entityModelOfOrderResponse = this.toModel(orderResponse);
        return ResponseEntity.created(linkTo(methodOn(OrderController.class).get(orderResponse.getId())).toUri())
                .body(entityModelOfOrderResponse);
    }

    /**
     * Updates an existing order with the new details provided in the request.
     *
     * @param id the unique identifier of the order to be updated
     * @param orderRequest the request object containing the updated details of the order
     * @return a {@code ResponseEntity} containing an {@code EntityModel} of {@code OrderResponse},
     *         which includes the updated details of the order
     */
    // PUT - update - /api/orders/{id}
    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<OrderResponse>> update(@PathVariable Long id,
                                                             @Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.update(orderMapper.toEntity(orderRequest));
        EntityModel<OrderResponse> entityModelOfOrderResponse = this.toModel(orderMapper.toResponse(order));
        return ResponseEntity.ok(entityModelOfOrderResponse);
    }

    /**
     * Deletes the order with the specified ID.
     *
     * @param id the unique identifier of the order to be deleted
     * @return a {@code ResponseEntity} with no content to indicate the order was successfully deleted
     */
    // DELETE - delete - /api/orders/{id}
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Converts an {@code OrderResponse} object into an {@code EntityModel<OrderResponse>}
     * with relevant HATEOAS links.
     *
     * @param response the {@code OrderResponse} object to be wrapped into an {@code EntityModel}
     * @return an {@code EntityModel<OrderResponse>} containing the {@code OrderResponse}
     *         with associated HATEOAS links
     */
    private EntityModel<OrderResponse> toModel(OrderResponse response) {
        return EntityModel.of(response,
                linkTo(methodOn(OrderController.class).get(response.getId())).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(OrderController.class).list(null)).withRel("orders")
                        .withType(HttpMethod.GET.name())
                );
    }

}