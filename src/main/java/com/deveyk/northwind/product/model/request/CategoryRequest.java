package com.deveyk.northwind.product.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {

    @NotBlank(message = "Name is required")
    @Max(value = 15, message = "Name is too long")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(max = 500, message = "Description is too long")
    private String description;


}
