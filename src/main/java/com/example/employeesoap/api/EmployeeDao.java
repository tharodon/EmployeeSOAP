package com.example.employeesoap.api;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exception.EmployeeNotFoundException;

public interface EmployeeDao {
    Employee findEmployeeById(String id) throws EmployeeNotFoundException;

    void save(Employee employee);

    Employee update(Employee employee);

    void delete(String id);
}
