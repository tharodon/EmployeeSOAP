package com.example.employeesoap.mapper;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.InvalidPositionException;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;

public interface EmployeeMapper {
    Employee fromEmployeeDto(EmployeeDto dto) throws InvalidPositionException;

    EmployeeResponse getResponseFromEmployee(Employee employee);
}
