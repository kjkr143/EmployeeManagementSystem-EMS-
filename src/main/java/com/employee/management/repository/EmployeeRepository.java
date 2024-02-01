package com.employee.management.repository;

import com.employee.management.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContainingIgnoreCase(String name);
}
