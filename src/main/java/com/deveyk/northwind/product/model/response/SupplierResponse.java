package com.deveyk.northwind.product.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierResponse {

    private Long id;
    private String companyName;
    private String contactName;
    private String contactTitle;
    private String address;
    private String country;
    private String phone;
    private String homepage;


}
