/* (C)2022 */
package com.example.employeesoap.api;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

public interface EmployeeMapper {

    EmployeeDto employeeToEmployeeDto(Employee employee);

    Employee employeeDtoToEmployee(EmployeeDto employeeDto);
}
