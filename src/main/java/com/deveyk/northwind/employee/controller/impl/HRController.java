package com.deveyk.northwind.employee.controller.impl;

import com.deveyk.northwind.employee.controller.IHRController;
import com.deveyk.northwind.employee.mapper.EmployeeMapper;
import com.deveyk.northwind.employee.model.entity.Employee;
import com.deveyk.northwind.employee.model.entity.EmployeeDetails;
import com.deveyk.northwind.employee.model.request.EmployeeCreateRequest;
import com.deveyk.northwind.employee.model.request.EmployeeDetailsUpdateRequest;
import com.deveyk.northwind.employee.model.request.EmployeeUpdateRequest;
import com.deveyk.northwind.employee.model.response.EmployeeHrResponse;
import com.deveyk.northwind.employee.service.IHRService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/hr")
@RequiredArgsConstructor
public class HRController implements IHRController {

    private final IHRService hrService;
    private final EmployeeMapper employeeMapper;

    /**
     * Retrieves the details of an employee by their unique identifier.
     *
     * @param id the unique identifier of the employee to be retrieved
     * @return a {@link ResponseEntity} containing an {@link EntityModel} representation
     *         of the {@link EmployeeHrResponse} object along with relevant HATEOAS links
     */
    // GET - get - /api/hr/employees/{id}
    @Override
    @GetMapping("/employees/{id}")
    public ResponseEntity<EntityModel<EmployeeHrResponse>> get(@PathVariable Long id) {
        EmployeeHrResponse employeeHrResponse = employeeMapper.toHrResponse(hrService.findById(id));
        EntityModel<EmployeeHrResponse> entityModelOfEmployeeResponse = EntityModel.of(employeeHrResponse,
                linkTo(methodOn(HRController.class).list(null)).withRel("employees")
                        .withType(HttpMethod.GET.name())
                );
        return ResponseEntity.ok(entityModelOfEmployeeResponse);
    }

    /**
     * Retrieves a paginated and sorted collection of employees.
     *
     * @param pageable the pagination and sorting information, including page size, sort field,
     *                 and sort direction. Defaults to a page size of 10, sorting by "firstName"
     *                 in ascending order if not provided.
     * @return a {@link ResponseEntity} containing a {@link CollectionModel}
     *         of {@link EntityModel} representations of {@link EmployeeHrResponse} objects
     *         with HATEOAS links.
     */
    // GET - list - /api/hr/employees
    @Override
    @GetMapping("/employees")
    public ResponseEntity<CollectionModel<EntityModel<EmployeeHrResponse>>> list(
            @PageableDefault(size = 10, sort = "firstName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<EmployeeHrResponse>> employeesResponse = hrService.findAll(pageable).stream()
                .map(employeeMapper::toHrResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<EmployeeHrResponse>> collectionModelOfEmployeesResponse = CollectionModel.of(employeesResponse,
                linkTo(methodOn(HRController.class).list(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
        return ResponseEntity.ok(collectionModelOfEmployeesResponse);
    }

    /**
     * Hires a new employee based on the provided request data.
     * This method is secured and accessible only to users with the role ADMIN,
     * or users with both HR and MANAGER roles.
     *
     * @param employeeCreateRequest the data transfer object containing information
     *                              required to create a new employee
     * @return a {@link ResponseEntity} containing an {@link EntityModel} representation
     *         of the {@link EmployeeHrResponse} object, which includes HATEOAS links,
     *         along with a "201 Created" HTTP status
     */
    // POST - hire - /api/hr/employees
    @Override
    @PostMapping("/employees")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('HR') and hasRole('MANAGER'))")
    public ResponseEntity<EntityModel<EmployeeHrResponse>> hire(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
        Employee employee = hrService.hire(employeeMapper.toEntity(employeeCreateRequest));
        EmployeeHrResponse employeeHrResponse = employeeMapper.toHrResponse(employee);
        EntityModel<EmployeeHrResponse> entityModelOfEmployeeResponse = this.toModel(employeeHrResponse);

        return ResponseEntity.created(linkTo(methodOn(HRController.class).get(null)).toUri())
                .body(entityModelOfEmployeeResponse);
    }

    /**
     * Fires an employee identified by their unique ID. This method is secured and
     * can only be accessed by users with the ADMIN role or users having both HR
     * and MANAGER roles.
     *
     * @param id the unique identifier of the employee to be fired
     * @return a {@link ResponseEntity} with HTTP status 204 (No Content) when the operation is successful
     */
    // DELETE - fire - /api/hr/employees/{id}
    @Override
    @DeleteMapping("/employees/{id}")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('HR') and hasRole('MANAGER'))")
    public ResponseEntity<Void> fire(@PathVariable Long id) {
        hrService.fire(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Updates the details of an existing employee based on the provided unique identifier.
     * Accepts updated employee information and returns the updated employee data.
     *
     * @param id the unique identifier of the employee to be updated
     * @param employeeUpdateRequest the data transfer object containing the updated employee details
     * @return a {@link ResponseEntity} containing an {@link EntityModel} representation
     *         of the updated {@link EmployeeHrResponse} object along with relevant HATEOAS links
     */
    // PUT - update - employee - /api/hr/employees/{id}
    @Override
    @PutMapping("/employees/{id}")
    public ResponseEntity<EntityModel<EmployeeHrResponse>> updateEmployee(@PathVariable Long id,
                                                                          @Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) {
        Employee employeeUpdate = employeeMapper.toEntity(employeeUpdateRequest);
        Employee updatedEmployee = hrService.updateEmployee(id, employeeUpdate);
        EmployeeHrResponse employeeHrResponse = employeeMapper.toHrResponse(updatedEmployee);
        EntityModel<EmployeeHrResponse> entityModelOfEmployeeResponse = this.toModel(employeeHrResponse);
        return ResponseEntity.ok(entityModelOfEmployeeResponse);
    }

    /**
     * Updates the details of an existing employee based on the provided employeeId and request body.
     * The employee details are updated with the information provided, and the updated employee information
     * is returned in a response entity.
     *
     * @param employeeId The unique identifier of the employee whose details are to be updated.
     * @param employeeDetailsUpdateRequest The request body containing the updated employee details.
     *                                      It must be a valid instance of {@link EmployeeDetailsUpdateRequest}.
     * @return A ResponseEntity containing an EntityModel of the updated employee information.
     */
    // PUT - update - employee details - /api/hr/employees/{employeeId}/details
    @Override
    @PutMapping("/employees/{employeeId}/details")
    public ResponseEntity<EntityModel<EmployeeHrResponse>> updateEmployeeDetails(@PathVariable Long employeeId,
                                                                                 @Valid @RequestBody EmployeeDetailsUpdateRequest employeeDetailsUpdateRequest) {
        EmployeeDetails employeeDetailsUpdate = employeeMapper.toEntity(employeeDetailsUpdateRequest);
        hrService.updateEmployeeDetails(employeeId, employeeDetailsUpdate);
        Employee updatedEmployee = hrService.findById(employeeId);
        EmployeeHrResponse employeeHrResponse = employeeMapper.toHrResponse(updatedEmployee);
        EntityModel<EmployeeHrResponse> entityModelOfEmployeeResponse = this.toModel(employeeHrResponse);
        return ResponseEntity.ok(entityModelOfEmployeeResponse);
    }

    /**
     * Updates the bonus of an employee based on the provided bonus rate.
     * This method is restricted to users with the specified roles.
     *
     * @param id the ID of the employee whose bonus is being updated
     * @param bonusRate the bonus rate to be applied to the employee's bonus
     * @return a ResponseEntity containing an EntityModel of the updated employee's HR response
     */
    // PATCH - /api/hr/employees/{id}/bonus?bonusRate= - zam
    @Override
    @PatchMapping("/employees/{id}/bonus")
    @PreAuthorize("hasRole('ADMIN') or (hasRole('HR') and hasRole('MANAGER'))")
    public ResponseEntity<EntityModel<EmployeeHrResponse>> bonus(@PathVariable Long id,
                                                                 @RequestParam(value = "bonusRate", required = true) Long bonusRate) {
        Employee updatedEmployee = hrService.bonus(id, bonusRate);
        EmployeeHrResponse employeeHrResponse = employeeMapper.toHrResponse(updatedEmployee);
        EntityModel<EmployeeHrResponse> entityModelOfEmployeeResponse = this.toModel(employeeHrResponse);
        return ResponseEntity.ok(entityModelOfEmployeeResponse);
    }

    /**
     * Converts an EmployeeHrResponse object into an EntityModel representation,
     * including a self-referential link.
     *
     * @param employeeHrResponse the EmployeeHrResponse object to be converted into an EntityModel
     * @return an EntityModel containing the EmployeeHrResponse and a self-referential link
     */
    private EntityModel<EmployeeHrResponse> toModel(EmployeeHrResponse employeeHrResponse) {
        return EntityModel.of(employeeHrResponse,
                linkTo(methodOn(HRController.class).get(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
        );
    }


}
