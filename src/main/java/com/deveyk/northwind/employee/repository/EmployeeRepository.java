package com.deveyk.northwind.employee.repository;

import com.deveyk.northwind.employee.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, PagingAndSortingRepository<Employee, Long> {


}
