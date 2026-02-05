package com.deveyk.northwind.employee.controller;

import com.deveyk.northwind.common.response.ErrorResponse;
import com.deveyk.northwind.employee.model.response.EmployeePublicResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "Employees",
     description = "Employee API endpoints for managing employee information and hierarchical relationships")
public interface IEmployeeController {

    @Operation(
            summary = "Get employee by ID",
            description = "Retrieves detailed information about a specific employee",
            tags = {"Employees"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee found and returned successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeePublicResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<EmployeePublicResponse>> get(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "List all employees",
            description = "Retrieves a paginated list of all employees sorted by last name",
            tags = {"Employees"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of employees retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeePublicResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<EmployeePublicResponse>>> list(
            @Parameter(
                description = "Pagination parameters (default: page=0, size=10, sort=lastName,asc)",
                schema = @Schema(implementation = Pageable.class)
            )
            @PageableDefault(size = 10, sort = "lastName", direction = Sort.Direction.ASC) Pageable pageable
    );

    @Operation(
            summary = "Get employee's supervisor",
            description = "Retrieves information about the employee's direct supervisor (reports to)",
            tags = {"Employees"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Supervisor information retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeePublicResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee or supervisor not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<EmployeePublicResponse>> getReportsTo(
            @Parameter(description = "ID of the employee whose supervisor is to be retrieved", required = true, example = "1")
            @PathVariable Long id
    );
}