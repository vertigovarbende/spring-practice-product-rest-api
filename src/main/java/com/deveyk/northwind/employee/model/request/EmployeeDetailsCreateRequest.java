package com.deveyk.northwind.employee.model.request;

import com.deveyk.northwind.common.validation.Phone;
import com.deveyk.northwind.common.validation.PostalCode;
import com.deveyk.northwind.employee.model.entity.Employee;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDetailsCreateRequest {

    @NotNull(message = "Hire date is required")
    @PastOrPresent(message = "Hire date cannot be in the future")
    private LocalDate hireDate;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 60, message = "Address can be max 60 characters")
    private String address;

    @NotBlank(message = "City cannot be blank")
    @Size(max = 15, message = "City can be max 15 characters")
    private String city;

    @Size(max = 15, message = "Region can be max 15 characters")
    private String region;

    @PostalCode(message = "Postal code must be numeric and between 4–10 digits")
    private String postalCode;

    @NotBlank(message = "Country cannot be blank")
    @Size(max = 15, message = "Country can be max 15 characters")
    private String country;

    @Phone
    private String homePhone;

    @Size(max = 4, message = "Extension can be max 4 characters")
    private String extension;

    @Size(max = 500, message = "Notes can be max 500 characters")
    private String notes;

}
