package com.deveyk.northwind.product.mapper;

import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.model.request.CategoryRequest;
import com.deveyk.northwind.product.model.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);

    Category toEntity(CategoryRequest categoryRequest);
}
