package com.deveyk.northwind.employee.model.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeePublicResponse {

    private Long id;
    private String lastName;
    private String firstName;
    private String title;
    private String titleOfCourtesy;
    private LocalDate birthDate;

}
