package com.deveyk.northwind.customer.mapper;

import com.deveyk.northwind.customer.model.response.CustomerCategoryResponse;
import com.deveyk.northwind.product.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerCategoryMapper {

    CustomerCategoryResponse toResponse(Category category);

}
