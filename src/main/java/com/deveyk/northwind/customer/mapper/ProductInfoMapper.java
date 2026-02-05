package com.deveyk.northwind.customer.mapper;

import com.deveyk.northwind.customer.model.response.ProductInfoResponse;
import com.deveyk.northwind.product.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductInfoMapper {

    ProductInfoResponse toResponse(Product product);

}
