package com.deveyk.northwind.customer.mapper;

import com.deveyk.northwind.customer.model.response.CustomerProductResponse;
import com.deveyk.northwind.product.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = CustomerCategoryMapper.class
)
public interface CustomerProductMapper {

    @Mappings(
            @Mapping(target = "category", source = "category")
    )
    CustomerProductResponse toResponse(Product product);


}
