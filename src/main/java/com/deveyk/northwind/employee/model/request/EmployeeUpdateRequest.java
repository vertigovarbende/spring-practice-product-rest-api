package com.deveyk.northwind.employee.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeUpdateRequest {

    @Size(max = 20, message = "Last name is too long")
    private String lastName;

    @Size(max = 10, message = "First name is too long")
    private String firstName;

    @Size(max = 30, message = "Title is too long")
    private String title;

    @Size(max = 25, message = "Title of courtesy is too long")
    private String titleOfCourtesy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private LocalDate birthDate;

    private BigDecimal salary;

}
