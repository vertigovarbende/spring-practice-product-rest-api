package com.deveyk.northwind.employee.service;

import com.deveyk.northwind.employee.model.entity.Employee;
import com.deveyk.northwind.employee.model.entity.EmployeeDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IHRService {

    Employee findById(Long id);

    Page<Employee> findAll(Pageable pageable);

    Employee hire(Employee employee);

    void fire(Long id);

    Employee updateEmployee(Long id, Employee updatedEmployee);

    EmployeeDetails updateEmployeeDetails(Long employeeId, EmployeeDetails updatedEmployeeDetails);

    Employee bonus(Long id, Long bonusRate);



}
