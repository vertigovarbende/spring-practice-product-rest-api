package com.deveyk.northwind.customer.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;

}
