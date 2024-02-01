package com.employee.management.controller;

import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/employees")
@Tag(
        name = " REST APIs for Employee Details",
        description = "REST APIs in Employee Details to CREATE, FETCH, DELETE, UPDATE"
)
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @Operation(
            summary = "Fetch Employee Details REST API",
            description = "REST API to fetch Employee details based on a Name by sort or search"
    )
    @GetMapping("/")
    public List<Employee> getAllEmployees(@RequestParam(required = false, defaultValue = "name") String sortBy,
                                          @RequestParam(required = false) String searchName) {
        List<Employee> employees;
        if (searchName != null && !searchName.isEmpty()) {
            employees = employeeService.searchEmployeesByName(searchName);
        } else {
            employees = employeeService.getAllEmployees(sortBy);
        }
        return employees;
    }

    @Operation(
            summary = "Fetch Employee Details REST API",
            description = "REST API to fetch Employee details based on ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok().body(employee);
    }

    @Operation(
            summary = "Create Employee details REST API",
            description = "REST API to create new Employee Details"
    )
    @PostMapping("/")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @Operation(
            summary = "Update Employee Details REST API",
            description = "REST API to fetch Employee details based on ID"
    )
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
        return ResponseEntity.ok(updatedEmployee);
    }

    @Operation(
            summary = "Delete Employee Details REST API",
            description = "REST API to fetch Employee details based on ID"
    )
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
