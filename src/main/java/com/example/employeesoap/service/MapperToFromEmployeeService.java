package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;

//todo перенеси все интерфейсы в отдельный пакет api
public interface MapperToFromEmployeeService { //todo название EmployeeMapper в пакете mapper
    Employee fromEmployeeDto(EmployeeDto dto);
    EmployeeResponse getResponseFromEmployee(Employee employee);
}
