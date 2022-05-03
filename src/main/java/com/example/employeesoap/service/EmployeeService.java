package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;

public interface EmployeeService {
    Employee findEmployeeById(Long id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(Long id);
}
