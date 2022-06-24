package com.example.employeesoap.api;


import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

public interface ValidatorFieldsService {
    EmployeeDto validCheck(Employee employee);
}
