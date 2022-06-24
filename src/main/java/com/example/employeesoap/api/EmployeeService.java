package com.example.employeesoap.api;


import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> addEmployees(List<Employee> employees);

    EmployeeDto updateEmployee(Employee employee);

    void deleteEmployee(String id);

    EmployeeDto getEmployeeById(String id);
}
