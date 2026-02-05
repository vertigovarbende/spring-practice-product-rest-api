package com.deveyk.northwind.customer.mapper;

import com.deveyk.northwind.customer.mapper.resolver.ProductResolver;
import com.deveyk.northwind.customer.model.response.CustomerOrderResponse;
import com.deveyk.northwind.customer.model.response.CustomerProductResponse;
import com.deveyk.northwind.order.model.entity.Order;
import com.deveyk.northwind.order.model.entity.OrderDetails;
import com.deveyk.northwind.product.mapper.resolver.SupplierResolver;
import com.deveyk.northwind.product.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring",
        uses = { OrderDetails.class, BigDecimal.class, ProductResolver.class})
public interface CustomerOrderMapper {

    @Mappings({
            @Mapping(
                    target = "discount",
                    expression = "java(" +
                            "order.getOrderDetails().stream()" +
                            ".map(com.deveyk.northwind.order.model.entity.OrderDetails::getDiscount)" +
                            ".reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add)" +
                            ")"
            ),
            @Mapping(
                    target = "total",
                    expression =
                            "java(order.getOrderDetails().stream()" +
                                    ".map(com.deveyk.northwind.order.model.entity.OrderDetails::getTotal)" +
                                    ".reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add))"
            ),
            @Mapping(target = "products", source = "orderDetails", qualifiedByName = "resolveProduct")
    })
    CustomerOrderResponse toResponse(Order order);


}
