package com.example.employeesoap.controller;

import com.example.employeesoap.IntegrationTest;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.employeesoap.type.Status.ERROR;
import static com.example.employeesoap.type.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest extends IntegrationTest {

    private final EmployeeController employeeController;

    private final EmployeeRepository employeeRepository;

    private final EmployeeDto requestAnna = EmployeeDto.builder()
            .uid("4")
            .position("Manager")
            .age("44")
            .name("Anna")
            .surname("Volatilisina")
            .salary("75000")
            .status(SUCCESS)
            .tasksUID(Arrays.toString(new Long[0]))
            .build();

    private final Employee requestPavel = Employee.builder()
            .position("Junior")
            .age(19L)
            .name("Pavel")
            .surname("Matronus")
            .salary(65_000L)
            .build();
    private final Employee requestIvan = Employee.builder()
            .position("Manager")
            .age(17L)
            .name("Ivan")
            .surname("Pupkin")
            .salary(65_000L)
            .build();

    @Autowired
    public EmployeeControllerTest(EmployeeController employeeController, EmployeeRepository employeeRepository) {
        this.employeeController = employeeController;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void addEmployee() {
        ResponseEntity<List<EmployeeDto>> responseEntity = (ResponseEntity<List<EmployeeDto>>) employeeController.addEmployee(Arrays.asList(requestIvan, requestPavel));
        assertSame(Objects.requireNonNull(responseEntity.getBody()).get(0).getStatus(), ERROR);
        assertSame(Objects.requireNonNull(responseEntity.getBody()).get(1).getStatus(), SUCCESS);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void updateEmployeeTest() {
        ResponseEntity<EmployeeDto> responseEntity = (ResponseEntity<EmployeeDto>) employeeController.updateEmployee(requestIvan);
        assertSame(Objects.requireNonNull(responseEntity.getBody()).getStatus(), ERROR);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        responseEntity = (ResponseEntity<EmployeeDto>) employeeController.updateEmployee(requestPavel);
        assertSame(Objects.requireNonNull(responseEntity.getBody()).getStatus(), SUCCESS);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "test")
    void getEmployeeWithExistsEmployee() {
        ResponseEntity<?> response = employeeController.getEmployee("4");
        assertTrue(Objects.equals(requestAnna, response.getBody()) && response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "test")
    void getEmployeeNotExistEmployee() {
        assertThrows(Exception.class, () -> employeeController.getEmployee("not exist uid"));
    }

    @Test
    void deleteExistsEmployeeTest() {
        employeeController.deleteEmployee("1");
        employeeController.deleteEmployee("2");
        employeeController.deleteEmployee("3");
        employeeController.deleteEmployee("4");
        assertFalse(employeeRepository.findByUid("1").isPresent());
        assertFalse(employeeRepository.findByUid("2").isPresent());
        assertFalse(employeeRepository.findByUid("3").isPresent());
        assertFalse(employeeRepository.findByUid("4").isPresent());
    }
}