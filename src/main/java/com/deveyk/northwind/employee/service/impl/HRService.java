package com.deveyk.northwind.employee.service.impl;

import com.deveyk.northwind.employee.exception.InvalidBonusRateException;
import com.deveyk.northwind.employee.model.entity.Employee;
import com.deveyk.northwind.employee.model.entity.EmployeeDetails;
import com.deveyk.northwind.employee.repository.EmployeeRepository;
import com.deveyk.northwind.employee.service.IHRService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class HRService implements IHRService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    @Override
    public Page<Employee> findAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee hire(Employee employee) {
        return employeeRepository.save(Employee.builder()
                        .lastName(employee.getLastName())
                        .firstName(employee.getFirstName())
                        .title(employee.getTitle())
                        .titleOfCourtesy(employee.getTitleOfCourtesy())
                        .birthDate(employee.getBirthDate())
                .build());
    }

    @Override
    public void fire(Long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Employee not found");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
       if (employeeRepository.findById(id).isEmpty()) {
           throw new EntityNotFoundException("Employee not found");
       }
       return employeeRepository.save(updatedEmployee);
    }

    // void????
    @Override
    public EmployeeDetails updateEmployeeDetails(Long employeeId, EmployeeDetails updatedEmployeeDetails) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        if (employee.getEmployeeDetails() == null) {
            throw new EntityNotFoundException("Employee details not found for this employee");
        }
        employee.setEmployeeDetails(updatedEmployeeDetails);
        employeeRepository.save(employee);
        return employee.getEmployeeDetails();
    }


    // Domain and Entity???
    // This method doesn't look like a coordinative method????
    @Override
    public Employee bonus(Long id, Long bonusRate) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        if (employee.getSalary() == null) {
            throw new IllegalArgumentException("Employee salary is not set. Cannot calculate bonus.");
        }

        if (bonusRate == null || bonusRate <= 0) {
            throw new InvalidBonusRateException("Bonus rate must be a positive number");
        }
        BigDecimal bonusAmount = employee.getSalary()
                .multiply(BigDecimal.valueOf(bonusRate))
                .divide(BigDecimal.valueOf(100), 2, java.math.RoundingMode.HALF_UP);

        BigDecimal newSalary = employee.getSalary().add(bonusAmount);
        employee.setSalary(newSalary);

        return employeeRepository.save(employee);
    }


}
