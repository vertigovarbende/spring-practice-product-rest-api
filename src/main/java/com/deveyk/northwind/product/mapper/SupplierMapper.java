package com.deveyk.northwind.product.mapper;

import com.deveyk.northwind.product.model.entity.Supplier;
import com.deveyk.northwind.product.model.request.SupplierRequest;
import com.deveyk.northwind.product.model.response.SupplierResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierResponse toResponse(Supplier supplier);

    Supplier toEntity(SupplierRequest supplierRequest);


}
