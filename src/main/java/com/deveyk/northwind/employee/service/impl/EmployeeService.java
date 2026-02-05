package com.deveyk.northwind.employee.service.impl;

import com.deveyk.northwind.employee.model.entity.Employee;
import com.deveyk.northwind.employee.repository.EmployeeRepository;
import com.deveyk.northwind.employee.service.IEmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + id));
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getReportsTo(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id " + employeeId));
        Employee reportsTo = employee.getReportsTo();
        if (reportsTo == null) {
            return employee;    // he is the manager
        }
        return reportsTo;
    }

}
