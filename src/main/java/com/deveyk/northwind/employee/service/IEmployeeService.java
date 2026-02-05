package com.deveyk.northwind.employee.service;

import com.deveyk.northwind.employee.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {

    Employee findById(Long id);

    Page<Employee> findAll(Pageable pageable);

    Employee getReportsTo(Long employeeId);


}
