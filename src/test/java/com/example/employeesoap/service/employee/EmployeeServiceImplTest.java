package com.example.employeesoap.service.employee;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.repository.EmployeeRepository;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.Status.ERROR;
import static com.example.employeesoap.type.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    void addValidEmployeesShouldGetSuccessStatus() {
        Employee employee1 = getLegalJunior();
        Employee employee2 = getLegalManager();
        assertEquals(SUCCESS, employeeService.addEmployees(Arrays.asList(employee1, employee2)).get(0).getStatus());
        assertEquals(SUCCESS, employeeService.addEmployees(Arrays.asList(employee1, employee2)).get(1).getStatus());
    }

    @Test
    void addEmployeesIllegalEmployeeShouldGetErrorStatus() {
        Employee employee1 = getLegalJunior();
        employee1.setSalary(35_000L);
        Employee employee2 = getLegalSenior();
        employee2.setAge(24L);
        assertEquals(ERROR, employeeService.addEmployees(Arrays.asList(employee1, employee2)).get(0).getStatus());
        assertEquals(ERROR, employeeService.addEmployees(Arrays.asList(employee1, employee2)).get(1).getStatus());
    }

    @Test
    void updateLegalEmployeeShouldGetSuccessStatus() {
        Employee employee = getLegalJunior();
        assertEquals(SUCCESS, employeeService.updateEmployee(employee).getStatus());
    }

    @Test
    void updateEmployeeWithNullableAgeShouldGetErrorStatus() {
        Employee employee = getLegalJunior();
        employee.setAge(null);
        assertEquals(ERROR, employeeService.updateEmployee(employee).getStatus());
    }

    @Test
    void deleteEmployeeShouldDeleteInDataBase() {
        employeeService.deleteEmployee(VICTOR_UID);
        assertFalse(employeeRepository.findByUid(VICTOR_UID).isPresent());
    }

    @Test
    void getEmployeeById() {
        assertEquals(EMPLOYEE_DTO_ANNA, employeeService.getEmployeeById(ANNA_UID));
    }
}
