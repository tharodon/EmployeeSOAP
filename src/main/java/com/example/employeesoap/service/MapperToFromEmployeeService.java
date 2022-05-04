package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;

public interface MapperToFromEmployeeService {
    Employee fromEmployeeDto(EmployeeDto dto);
    EmployeeResponse getResponseFromEmployee(Employee employee);
}
