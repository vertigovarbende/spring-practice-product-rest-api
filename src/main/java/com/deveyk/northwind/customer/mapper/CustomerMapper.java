package com.deveyk.northwind.customer.mapper;

import com.deveyk.northwind.customer.model.entity.Customer;
import com.deveyk.northwind.customer.model.response.CustomerResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponse toResponse(Customer customer);
}
