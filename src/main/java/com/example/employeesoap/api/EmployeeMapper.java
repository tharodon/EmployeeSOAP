package com.example.employeesoap.api;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

//todo название EmployeeMapper
//done
public interface EmployeeMapper {

    //todo название employeeToEmployeeDto
    //done
    EmployeeDto employeeToEmployeeDto(Employee employee);
}
