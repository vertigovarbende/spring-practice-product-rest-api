package com.deveyk.northwind.product.model.request;

import com.deveyk.northwind.common.validation.Phone;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SupplierRequest {

    @NotBlank(message = "Company name is required")
    @Max(value = 40, message = "Company name is too long")
    private String companyName;

    @NotBlank(message = "Contact name is required")
    @Max(value = 30, message = "Contact name is too long")
    private String contactName;

    @NotBlank(message = "Contact title is required")
    @Max(value = 30, message = "Contact title is too long")
    private String contactTitle;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address is too long")
    private String address;

    @NotBlank(message = "City is required")
    @Max(value = 15, message = "City is too long")
    private String city;

    @NotBlank(message = "Country is required")
    @Max(value = 15, message = "Country is too long")
    private String country;

    @NotBlank(message = "Phone is required")
    @Phone
    private String phone;

}
