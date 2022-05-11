package com.example.employeesoap.api;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

public interface MapperFromEmployeeService { //todo название EmployeeMapper
    EmployeeDto convertFromEmployee(Employee employee); //todo название employeeToEmployeeDto
}
