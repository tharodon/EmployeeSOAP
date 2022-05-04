package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;

public interface MapperService {
    //todo fromEmployeeDto. convertFromDto так непонятно, что это за Dto
    Employee convertFromDto(EmployeeDto dto);
    //todo плохое название для маппера
    EmployeeResponse getResponse(Employee employee);
}
