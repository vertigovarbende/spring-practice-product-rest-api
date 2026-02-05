package com.deveyk.northwind.customer.controller.impl;

import com.deveyk.northwind.customer.controller.ICustomerController;
import com.deveyk.northwind.customer.mapper.CustomerMapper;
import com.deveyk.northwind.customer.mapper.CustomerOrderMapper;
import com.deveyk.northwind.customer.mapper.ProductInfoMapper;
import com.deveyk.northwind.customer.model.response.CustomerOrderResponse;
import com.deveyk.northwind.customer.model.response.CustomerResponse;
import com.deveyk.northwind.customer.model.response.ProductInfoResponse;
import com.deveyk.northwind.customer.service.ICustomerService;
import com.deveyk.northwind.product.model.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController implements ICustomerController {

    private final ICustomerService customerService;
    private final CustomerMapper customerMapper;
    private final ProductInfoMapper productInfoMapper;
    private final CustomerOrderMapper customerOrderMapper;

    /**
     * Retrieves customer-specific information.
     *
     * @param authentication the authentication object containing the currently authenticated user's details
     * @return a ResponseEntity containing an EntityModel of the customer's information
     */
    // GET - getInfo - /api/customers/info - ROLE_CUSTOMER
    @Override
    @GetMapping("/info")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<EntityModel<CustomerResponse>> getInfo(Authentication authentication) {
        CustomerResponse customerResponse = customerMapper.toResponse(customerService.getInfo(authentication));
        return ResponseEntity.ok(
                EntityModel.of(customerResponse,
                        linkTo(methodOn(CustomerController.class).getInfo(null)).withSelfRel()
                )
        );
    }

    /**
     * Retrieves the details of a specific customer by their unique identifier.
     *
     * @param id the unique identifier of the customer to retrieve
     * @return a ResponseEntity containing an EntityModel of the customer's details
     */
    // GET - getCustomer - /api/customers/{id} - ROLE_ADMIN, MANAGER, SALES
    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<EntityModel<CustomerResponse>> getCustomer(@PathVariable Long id) {
        CustomerResponse customerResponse = customerMapper.toResponse(customerService.findById(id));
        return ResponseEntity.ok(toModelForCustomer(customerResponse));
    }

    /**
     * Retrieves a paginated list of all customers. The list is sorted by company name in ascending order by default.
     * Accessible to users with roles ADMIN and SALES.
     *
     * @param pageable the pagination and sorting information for retrieving customers
     * @return a ResponseEntity containing a CollectionModel of EntityModels,
     *         where each EntityModel represents a customer's details
     */
    // GET - getAllCustomers - /api/customers - ROLE_ADMIN, MANAGER, SALES - pageable - cache
    @Override
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'SALES')")
    public ResponseEntity<CollectionModel<EntityModel<CustomerResponse>>> getAllCustomers(
            @PageableDefault(size = 10, sort = "companyName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<CustomerResponse>> customers = customerService.findAll(pageable).stream()
                .map(customerMapper::toResponse)
                .map(this::toModelForCustomer)
                .toList();
        CollectionModel<EntityModel<CustomerResponse>> collectionModelOfCustomers = CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).getAllCustomers(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );

        return ResponseEntity.ok(collectionModelOfCustomers);
    }

    /**
     * Retrieves the details of a specific product by its unique identifier.
     *
     * @param id the unique identifier of the product to be retrieved
     * @return a ResponseEntity containing an EntityModel of the product's details
     */
    // GET - getProduct - /api/customers/products/{id}
    @Override
    @GetMapping("/products/{id}")
    public ResponseEntity<EntityModel<ProductInfoResponse>> getProduct(@PathVariable Long id) {
        ProductInfoResponse productInfoResponse = productInfoMapper.toResponse(customerService.getProduct(id));
        return ResponseEntity.ok(toModelForProductInfo(productInfoResponse));
    }

    /**
     * Retrieves all products with pagination and sorting options.
     *
     * @param pageable a Pagable object containing pagination information such as page size, sort order,
     *                 and sorting property (default is sorting by 'reorderLevel' in ascending order).
     * @return a ResponseEntity containing a CollectionModel of EntityModel instances
     *         representing the ProductInfoResponse objects. Each product is wrapped in an EntityModel
     *         and the entire collection is wrapped in a CollectionModel,
     *         including a self-referencing link with the GET method type.
     */
    // GET - getAllProducts - /api/customers/products
    @Override
    @GetMapping("/products")
    public ResponseEntity<CollectionModel<EntityModel<ProductInfoResponse>>> getAllProducts(
            @PageableDefault(size = 10, sort = "reorderLevel", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<ProductInfoResponse>> productsInfoResponse = customerService.getAllProducts(pageable).stream()
                .map(productInfoMapper::toResponse)
                .map(this::toModelForProductInfo)
                .toList();
        CollectionModel<EntityModel<ProductInfoResponse>> collectionModelOfProductsInfoResponse = CollectionModel.of(
                productsInfoResponse,
                linkTo(methodOn(CustomerController.class).getAllProducts(null))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfProductsInfoResponse);
    }

    /**
     * Retrieves the order details for a customer based on the provided order ID.
     *
     * @param id the unique identifier of the order to retrieve
     * @param authentication the authentication object of the currently authenticated user
     * @return a ResponseEntity containing an EntityModel of CustomerOrderResponse representing the order details
     */
    // GET - getOrder - /api/customers/orders/{id}
    @Override
    @GetMapping("/orders/{id}")
    public ResponseEntity<EntityModel<CustomerOrderResponse>> getOrder(@PathVariable Long id,
                                                                       Authentication authentication) {
        CustomerOrderResponse customerOrderResponse = customerOrderMapper.toResponse(customerService.getOrder(id, authentication));
        return ResponseEntity.ok(toModelForCustomerOrder(customerOrderResponse));
    }

    /**
     * Retrieves a paginated list of all customer orders accessible by the authenticated customer.
     *
     * @param pageable the pagination and sorting information, including page size, sorting order,
     *                 and the fields to sort by
     * @param authentication the authentication object containing the details of the currently
     *                       authenticated customer
     * @return a {@code ResponseEntity} containing a {@code CollectionModel} of {@code EntityModel}
     *         representations of customer orders, with HATEOAS links for further navigation
     */
    // GET - getAllOrders - /api/customers/orders - ROLE_CUSTOMER - pageable - cache
    @Override
    @GetMapping("/orders")
    public ResponseEntity<CollectionModel<EntityModel<CustomerOrderResponse>>> getAllOrders(
            @PageableDefault(size = 10, sort = "orderStatus", direction = Sort.Direction.ASC) Pageable pageable,
            Authentication authentication
    ) {
        List<EntityModel<CustomerOrderResponse>> customerOrdersResponse = customerService.getAllOrders(pageable, authentication).stream()
                .map(customerOrderMapper::toResponse)
                .map(this::toModelForCustomerOrder)
                .toList();
        CollectionModel<EntityModel<CustomerOrderResponse>> collectionModelOfCustomerOrdersResponse = CollectionModel.of(customerOrdersResponse,
                          linkTo(methodOn(CustomerController.class).getAllOrders(null, null))
                                  .withSelfRel()
                                  .withType(HttpMethod.GET.name())
                );
        return ResponseEntity.ok(collectionModelOfCustomerOrdersResponse);
    }

    // POST - createOrderRequest


    /**
     * Cancels a customer order specified by its ID. Only users with roles 'ADMIN' or 'CUSTOMER' are authorized
     * to perform this action. The operation requires authentication.
     *
     * @param id the unique identifier of the order to be canceled
     * @param authentication the authentication object containing the user's security credentials
     * @return a ResponseEntity containing an EntityModel of the CustomerOrderResponse with the updated order details
     */
    // POST - cancelOrder - /api/customers/orders/{id}/cancel
    @PatchMapping("/orders/{id}/cancel")
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    public ResponseEntity<EntityModel<CustomerOrderResponse>> cancelOrder(@PathVariable Long id, Authentication authentication) {
        CustomerOrderResponse customerOrderResponse = customerOrderMapper.toResponse(customerService.cancelOrder(id, authentication));
        return ResponseEntity.ok(toModelForCustomerOrder(customerOrderResponse));
    }

    /**
     * Converts a CustomerResponse object into an EntityModel with appropriate links.
     *
     * @param customerResponse the CustomerResponse object to be converted into a model
     * @return an EntityModel containing the CustomerResponse and relevant links
     */
    private EntityModel<CustomerResponse> toModelForCustomer(CustomerResponse customerResponse) {
        return EntityModel.of(customerResponse,
                linkTo(methodOn(CustomerController.class).getAllCustomers(null)).withRel("customers")
                        .withType(HttpMethod.GET.name())
        );
    }

    /**
     * Converts the given ProductInfoResponse object into an EntityModel, including
     * a self-referencing link with the appropriate HTTP method type.
     *
     * @param productInfoResponse the ProductInfoResponse object containing product information
     * @return an EntityModel containing the product information and associated links
     */
    private EntityModel<ProductInfoResponse> toModelForProductInfo(ProductInfoResponse productInfoResponse) {
        return EntityModel.of(productInfoResponse,
                linkTo(methodOn(CustomerController.class).getProduct(productInfoResponse.getId())).withSelfRel()
                        .withType(HttpMethod.GET.name())
                );
    }

    /**
     * Converts a CustomerOrderResponse object into an EntityModel, adding relevant links for self,
     * retrieving all orders, and canceling the specific order.
     *
     * @param customerOrderResponse the response object containing customer order details to be wrapped
     *                              and enriched with HATEOAS links
     * @return an EntityModel wrapping the provided CustomerOrderResponse, with added HATEOAS links
     *         for self-reference, navigating to all orders, and canceling the current order
     */
    private EntityModel<CustomerOrderResponse> toModelForCustomerOrder(CustomerOrderResponse customerOrderResponse) {
        return EntityModel.of(customerOrderResponse,
                linkTo(methodOn(CustomerController.class).getOrder(customerOrderResponse.getId(), null))
                        .withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(CustomerController.class).getAllOrders(null, null))
                        .withRel("orders")
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(CustomerController.class).cancelOrder(customerOrderResponse.getId(), null))
                        .withRel("cancel")
                        .withType(HttpMethod.POST.name())
        );
    }
}
