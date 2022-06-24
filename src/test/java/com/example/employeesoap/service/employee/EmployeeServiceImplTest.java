package com.example.employeesoap.service.employee;

import static com.example.employeesoap.type.Status.ERROR;
import static com.example.employeesoap.type.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.repository.EmployeeRepository;
import com.example.employeesoap.support.IntegrationTest;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class EmployeeServiceImplTest extends IntegrationTest {

    private final EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplTest(
            EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void addValidEmployees() {
        Employee employee1 =
                Employee.builder()
                        .position("Junior")
                        .age(19L)
                        .name("Matis")
                        .surname("Matronus")
                        .salary(65_000L)
                        .build();
        Employee employee2 =
                Employee.builder()
                        .position("Manager")
                        .age(24L)
                        .name("Ivan")
                        .surname("Pupkin")
                        .grade("first")
                        .salary(70_000L)
                        .build();
        assertEquals(
                SUCCESS,
                employeeService
                        .addEmployees(Arrays.asList(employee1, employee2))
                        .get(0)
                        .getStatus());
        assertEquals(
                SUCCESS,
                employeeService
                        .addEmployees(Arrays.asList(employee1, employee2))
                        .get(1)
                        .getStatus());
    }

    @Test
    void addEmployeesShouldBeErrorIllegalEmployee() {
        Employee employee1 =
                Employee.builder()
                        .position("Junior")
                        .age(19L)
                        .name("Matis")
                        .surname("Matronus")
                        .salary(65_000L)
                        .build();
        Employee employee2 =
                Employee.builder()
                        .position("Manager")
                        .age(17L)
                        .name("Ivan")
                        .surname("Pupkin")
                        .salary(65_000L)
                        .build();
        assertEquals(
                SUCCESS,
                employeeService
                        .addEmployees(Arrays.asList(employee1, employee2))
                        .get(0)
                        .getStatus());
        assertEquals(
                ERROR,
                employeeService
                        .addEmployees(Arrays.asList(employee1, employee2))
                        .get(1)
                        .getStatus());
    }

    @Test
    void updateEmployee() {
        Employee employee =
                Employee.builder()
                        .uid("4")
                        .position("Junior")
                        .age(19L)
                        .name("Matis")
                        .surname("Matronus")
                        .salary(65_000L)
                        .build();
        assertEquals(SUCCESS, employeeService.updateEmployee(employee).getStatus());
        employee.setAge(null);
        assertEquals(ERROR, employeeService.updateEmployee(employee).getStatus());
    }

    @Test
    void deleteEmployeeShouldDeleteInDataBase() {
        employeeService.deleteEmployee("1");
        assertFalse(employeeRepository.findByUid("1").isPresent());
    }

    @Test
    void getExistEmployeeById() {
        EmployeeDto employee =
                EmployeeDto.builder()
                        .uid("4")
                        .position("Manager")
                        .age("44")
                        .name("Anna")
                        .surname("Volatilisina")
                        .salary("75000")
                        .status(SUCCESS)
                        .tasksUID(Arrays.toString(new Long[0]))
                        .build();
        assertEquals(employee, employeeService.getEmployeeById("4"));
    }
}
