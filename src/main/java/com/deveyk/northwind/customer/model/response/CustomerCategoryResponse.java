package com.deveyk.northwind.customer.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCategoryResponse {

    private String name;
    private String description;

}
