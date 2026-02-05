package com.deveyk.northwind.product.mapper.resolver;

import com.deveyk.northwind.product.model.entity.Category;
import com.deveyk.northwind.product.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CategoryResolver {

    private final CategoryRepository categoryRepository;

    @Named("resolveCategory")
    public Category resolve(String categoryName) {
        return categoryRepository.findByName(categoryName)
                .orElseThrow(() ->
                        new EntityNotFoundException("Category not found: " + categoryName));
    }
}
