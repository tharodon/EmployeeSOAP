package com.example.employeesoap.controller;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.repository.EmployeeRepository;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.Status.ERROR;
import static com.example.employeesoap.type.Status.SUCCESS;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeControllerTest extends IntegrationTest {

    private final EmployeeController employeeController;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeControllerTest(
            EmployeeController employeeController, EmployeeRepository employeeRepository) {
        this.employeeController = employeeController;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void addEmployee() {
        ResponseEntity<List<EmployeeDto>> responseEntity =
                (ResponseEntity<List<EmployeeDto>>)
                        employeeController.addEmployee(Arrays.asList(ILLEGAL_EMPLOYEE, LEGAL_EMPLOYEE));
        assertSame(Objects.requireNonNull(responseEntity.getBody()).get(0).getStatus(), ERROR);
        assertSame(Objects.requireNonNull(responseEntity.getBody()).get(1).getStatus(), SUCCESS);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void updateEmployeeTest() {
        ResponseEntity<EmployeeDto> responseEntity =
                (ResponseEntity<EmployeeDto>) employeeController.updateEmployee(ILLEGAL_EMPLOYEE);
        assertSame(Objects.requireNonNull(responseEntity.getBody()).getStatus(), ERROR);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        responseEntity =
                (ResponseEntity<EmployeeDto>) employeeController.updateEmployee(LEGAL_EMPLOYEE);
        assertSame(Objects.requireNonNull(responseEntity.getBody()).getStatus(), SUCCESS);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "test")
    void getEmployeeWithExistsEmployee() {
        ResponseEntity<?> response = employeeController.getEmployee(ANNA_UID);
        assertTrue(
                Objects.equals(LEGAL_EMPLOYEE_2, response.getBody())
                        && response.getStatusCode().is2xxSuccessful());
    }

    @Test
    @WithMockUser(username = "test")
    void getEmployeeNotExistEmployee() {
        assertThrows(Exception.class, () -> employeeController.getEmployee("not exist uid"));
    }

    @Test
    void deleteExistsEmployeeTest() {
        employeeController.deleteEmployee(VICTOR_UID);
        employeeController.deleteEmployee(OLEG_UID);
        employeeController.deleteEmployee(KARL_UID);
        employeeController.deleteEmployee(ANNA_UID);
        assertFalse(employeeRepository.findByUid(VICTOR_UID).isPresent());
        assertFalse(employeeRepository.findByUid(OLEG_UID).isPresent());
        assertFalse(employeeRepository.findByUid(KARL_UID).isPresent());
        assertFalse(employeeRepository.findByUid(ANNA_UID).isPresent());
    }
}
