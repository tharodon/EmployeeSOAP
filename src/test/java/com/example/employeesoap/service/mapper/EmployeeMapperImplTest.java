package com.example.employeesoap.service.mapper;

import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static com.example.employeesoap.type.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperImplTest {

    EmployeeMapper employeeMapper = new EmployeeMapperImpl();

    Employee employee = Employee.builder()
            .uid("5")
            .position("Junior")
            .age(19L)
            .name("Matis")
            .surname("Matronus")
            .salary(80_000L)
            .tasks(null)
            .build();

    EmployeeDto employeeDto = EmployeeDto.builder()
            .uid("5")
            .position("Junior")
            .age("19")
            .name("Matis")
            .surname("Matronus")
            .salary("80000")
            .status(SUCCESS)
            .tasksUID(Arrays.toString((long[]) null))
            .build();

    @Test
    void employeeToEmployeeDto() {
        assertEquals(employeeDto, employeeMapper.employeeToEmployeeDto(employee));
    }

    @Test
    void employeeDtoToEmployee() {
        assertEquals(employee, employeeMapper.employeeDtoToEmployee(employeeDto));
    }
}