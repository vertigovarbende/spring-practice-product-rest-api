package com.deveyk.northwind.employee.mapper;

import com.deveyk.northwind.employee.model.entity.Employee;
import com.deveyk.northwind.employee.model.entity.EmployeeDetails;
import com.deveyk.northwind.employee.model.request.EmployeeCreateRequest;
import com.deveyk.northwind.employee.model.request.EmployeeDetailsUpdateRequest;
import com.deveyk.northwind.employee.model.request.EmployeeUpdateRequest;
import com.deveyk.northwind.employee.model.response.EmployeeHrResponse;
import com.deveyk.northwind.employee.model.response.EmployeePublicResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeePublicResponse toPublicResponse(Employee employee);

    @Mappings({
            @Mapping(target = "hireDate", source = "employeeDetails.hireDate"),
            @Mapping(target = "address", source = "employeeDetails.address"),
            @Mapping(target = "city", source = "employeeDetails.city"),
            @Mapping(target = "region", source = "employeeDetails.region"),
            @Mapping(target = "postalCode", source = "employeeDetails.postalCode"),
            @Mapping(target = "country", source = "employeeDetails.country"),
            @Mapping(target = "homePhone", source = "employeeDetails.homePhone"),
            @Mapping(target = "extension", source = "employeeDetails.extension"),
            @Mapping(target = "notes", source = "employeeDetails.notes"),
    })
    EmployeeHrResponse toHrResponse(Employee employee);

    Employee toEntity(EmployeeCreateRequest employeeRequest);

    Employee toEntity(EmployeeUpdateRequest employeeUpdateRequest);

    EmployeeDetails toEntity(EmployeeDetailsUpdateRequest employeeDetailsUpdateRequest);

}
