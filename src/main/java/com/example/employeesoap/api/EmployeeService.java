package com.example.employeesoap.api;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;

public interface EmployeeService {
    Employee findEmployeeById(Long id) throws EmployeeNotFoundException;

    Employee save(Employee employee);

    Employee update(Employee employee);

    void delete(Employee employee);
}
