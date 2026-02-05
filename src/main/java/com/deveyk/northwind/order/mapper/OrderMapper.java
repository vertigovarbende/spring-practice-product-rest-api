package com.deveyk.northwind.order.mapper;

import com.deveyk.northwind.order.model.entity.Order;
import com.deveyk.northwind.order.model.request.OrderRequest;
import com.deveyk.northwind.order.model.response.OrderResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderResponse toResponse(Order order);

    Order toEntity(OrderRequest orderRequest);

}
