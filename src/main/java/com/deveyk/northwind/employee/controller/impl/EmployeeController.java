package com.deveyk.northwind.employee.controller.impl;

import com.deveyk.northwind.employee.controller.IEmployeeController;
import com.deveyk.northwind.employee.mapper.EmployeeMapper;
import com.deveyk.northwind.employee.model.response.EmployeePublicResponse;
import com.deveyk.northwind.employee.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController implements IEmployeeController {

    private final IEmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    /**
     * Retrieves an employee's public response by their unique identifier.
     *
     * @param id the unique identifier of the employee being retrieved
     * @return a {@link ResponseEntity} containing an {@link EntityModel} of the employee's public response
     */
    // GET - get - /api/employees/{id}
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EmployeePublicResponse>> get(@PathVariable Long id) {
        EmployeePublicResponse employeeResponse = this.employeeMapper.toPublicResponse(this.employeeService.findById(id));
        return ResponseEntity.ok(this.toModel(employeeResponse));
    }

    /**
     * Retrieves a paginated and sorted list of employees in a public response format.
     *
     * @param pageable the pagination and sorting information for retrieving the employees
     * @return a {@link ResponseEntity} containing a {@link CollectionModel} of {@link EntityModel} objects
     *         that wrap {@link EmployeePublicResponse}, representing the list of employees
     */
    // GET - list - /api/employees
    @Override
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<EmployeePublicResponse>>> list(
            @PageableDefault(size = 10, sort = "lastName", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<EntityModel<EmployeePublicResponse>> employeesResponse = employeeService.findAll(pageable).stream()
                .map(employeeMapper::toPublicResponse)
                .map(this::toModel)
                .toList();
        CollectionModel<EntityModel<EmployeePublicResponse>> collectionModelOfEmployeesResponse = CollectionModel.of(employeesResponse,
                linkTo(methodOn(EmployeeController.class).list(null)).withSelfRel()
                        .withType(HttpMethod.GET.name())
                );
        return ResponseEntity.ok(collectionModelOfEmployeesResponse);
    }

    /**
     * Retrieves the direct manager information of a specified employee.
     *
     * @param id the unique identifier of the employee whose manager information is being retrieved
     * @return a {@link ResponseEntity} containing an {@link EntityModel} of the employee's public response
     *         representing the direct manager's information
     */
    // GET - getReportsTo - /api/employees/{id}/reportsTo
    @Override
    @GetMapping("/{id}/territories")
    public ResponseEntity<EntityModel<EmployeePublicResponse>> getReportsTo(@PathVariable Long id) {
        EmployeePublicResponse employeeResponse = this.employeeMapper.toPublicResponse(this.employeeService.getReportsTo(id));
        return ResponseEntity.ok(this.toModel(employeeResponse));
    }

    /**
     * Converts an instance of {@link EmployeePublicResponse} into an {@link EntityModel}
     * containing the response and relevant HATEOAS links.
     *
     * @param response the employee public response to be wrapped in an {@link EntityModel}
     * @return an {@link EntityModel} of the given {@link EmployeePublicResponse}, including self and related links
     */
    private EntityModel<EmployeePublicResponse> toModel(EmployeePublicResponse response) {
        return EntityModel.of(response,
                linkTo(methodOn(EmployeeController.class).get(response.getId())).withSelfRel()
                        .withType(HttpMethod.GET.name()),
                linkTo(methodOn(EmployeeController.class).list(null)).withRel("employees")
                        .withType(HttpMethod.GET.name())
                );
    }

}
