package com.deveyk.northwind.employee.controller;

import com.deveyk.northwind.common.response.ErrorResponse;
import com.deveyk.northwind.employee.model.request.EmployeeCreateRequest;
import com.deveyk.northwind.employee.model.request.EmployeeDetailsUpdateRequest;
import com.deveyk.northwind.employee.model.request.EmployeeUpdateRequest;
import com.deveyk.northwind.employee.model.response.EmployeeHrResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "HR Management",
     description = "HR API endpoints for managing employee information, hiring, firing, and compensation")
public interface IHRController {

    @Operation(
            summary = "Get employee HR details by ID",
            description = "Retrieves detailed HR information about a specific employee including salary and benefits",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee HR information retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeHrResponse.class)
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
    ResponseEntity<EntityModel<EmployeeHrResponse>> get(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id
    );

    @Operation(
            summary = "List all employees with HR details",
            description = "Retrieves a paginated list of all employees with HR information",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of employees retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeHrResponse.class)
                    )
            )
    })
    ResponseEntity<CollectionModel<EntityModel<EmployeeHrResponse>>> list(
            @Parameter(
                    description = "Pagination parameters (default: page=0, size=10, sort=firstName,asc)",
                    schema = @Schema(implementation = Pageable.class)
            )
            Pageable pageable
    );

    @Operation(
            summary = "Hire new employee",
            description = "Creates a new employee record with all necessary HR information",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Employee hired successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeHrResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid employee data",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions to hire employees",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<EmployeeHrResponse>> hire(
            @Parameter(description = "New employee details", required = true)
            @Valid @RequestBody EmployeeCreateRequest employeeCreateRequest
    );

    @Operation(
            summary = "Terminate employee",
            description = "Terminates an employee's employment (soft delete)",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Employee terminated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions to terminate employees",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<Void> fire(@PathVariable Long id);

    @Operation(
            summary = "Apply bonus to employee salary",
            description = "Applies a percentage bonus to an employee's current salary",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Bonus applied successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeHrResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Employee not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Insufficient permissions to apply bonus",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)
                    )
            )
    })
    ResponseEntity<EntityModel<EmployeeHrResponse>> bonus(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Bonus percentage rate", required = true, example = "10")
            @RequestParam(value = "bonusRate", required = true) Long bonusRate
    );

    @Operation(
            summary = "Update employee basic information",
            description = "Updates an employee's basic information such as name, title, and contact details",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee information updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeHrResponse.class)
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
    ResponseEntity<EntityModel<EmployeeHrResponse>> updateEmployee(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated employee information", required = true)
            @Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest
    );

    @Operation(
            summary = "Update employee HR details",
            description = "Updates an employee's HR-specific details such as salary, benefits, and employment terms",
            tags = {"HR Management"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee HR details updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeHrResponse.class)
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
    ResponseEntity<EntityModel<EmployeeHrResponse>> updateEmployeeDetails(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long employeeId,
            @Parameter(description = "Updated HR details", required = true)
            @Valid @RequestBody EmployeeDetailsUpdateRequest employeeDetailsUpdateRequest
    );
}