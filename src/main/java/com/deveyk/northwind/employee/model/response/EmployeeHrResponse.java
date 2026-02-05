package com.deveyk.northwind.employee.model.response;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeHrResponse {

    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private LocalDate birthDate;

    private LocalDate hireDate;
    private String address;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    private String homePhone;
    private String extension;
    private String notes;

}
