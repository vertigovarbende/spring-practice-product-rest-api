package com.deveyk.northwind.employee.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeCreateRequest {

    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name is too long")
    private String lastName;

    @NotBlank(message = "First name is required")
    @Size(max = 10, message = "First name is too long")
    private String firstName;

    @Size(max = 30, message = "Title is too long")
    private String title;

    @Size(max = 25, message = "Title of courtesy is too long")
    private String titleOfCourtesy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-DD")
    private LocalDate birthDate;


}
