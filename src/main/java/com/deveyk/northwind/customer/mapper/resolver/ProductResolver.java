package com.deveyk.northwind.customer.mapper.resolver;

import com.deveyk.northwind.customer.mapper.CustomerProductMapper;
import com.deveyk.northwind.customer.model.response.CustomerProductResponse;
import com.deveyk.northwind.order.model.entity.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductResolver {

    private final CustomerProductMapper customerProductMapper;

    @Named("resolveProduct")
    public Set<CustomerProductResponse> resolve(Set<OrderDetails> orderDetails) {
        return orderDetails.stream()
                .map(detail -> {
                    CustomerProductResponse response = customerProductMapper.toResponse(detail.getProduct());
                    response.setQuantity(detail.getQuantity().longValue());
                    return response;
                })
                .collect(Collectors.toSet());
    }

    private Integer getQuantity(OrderDetails orderDetails) {
        return orderDetails.getQuantity();
    }
}


/*

    @Named("resolveProduct")
    public Set<CustomerProductResponse> resolve(Set<OrderDetails> orderDetails) {
        return orderDetails.stream()
                .map(OrderDetails::getProduct)
                .map(customerProductMapper::toResponse)

                .collect(Collectors.toSet());
    }
 */