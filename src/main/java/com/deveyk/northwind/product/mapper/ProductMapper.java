package com.deveyk.northwind.product.mapper;

import com.deveyk.northwind.product.mapper.resolver.CategoryResolver;
import com.deveyk.northwind.product.mapper.resolver.SupplierResolver;
import com.deveyk.northwind.product.model.entity.Product;
import com.deveyk.northwind.product.model.request.ProductRequest;
import com.deveyk.northwind.product.model.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(
        componentModel = "spring",
        uses = { CategoryResolver.class, SupplierResolver.class }
)
public interface ProductMapper {

    ProductResponse toResponse(Product product);

    @Mappings({
            @Mapping(target = "price",
                    expression = "java(new java.math.BigDecimal(productRequest.getPrice()))"),
            @Mapping(target = "unitsOnOrder", constant = "0L"),
            @Mapping(target = "reorderLevel", constant = "0L"),
            @Mapping(target = "discontinued", expression = "java(false)"),
            @Mapping(target = "category", source = "category", qualifiedByName = "resolveCategory"),
            @Mapping(target = "supplier", source = "supplier", qualifiedByName = "resolveSupplier")
    })
    Product toEntity(ProductRequest productRequest);

}
