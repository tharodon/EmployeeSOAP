package com.example.employeesoap.api;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface EmployeeDao {
    Employee findEmployeeById(String id) throws EmployeeNotFoundException;

    void save(List<Employee> employees);

    Employee update(Employee employee);

    void delete(String id);
}
