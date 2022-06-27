package com.example.employeesoap.dao;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exception.EmployeeNotFoundException;
import com.example.employeesoap.repository.EmployeeRepository;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.example.employeesoap.support.testdata.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeDaoImplTest extends IntegrationTest {
    private final EmployeeDao employeeDao;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDaoImplTest(EmployeeDao employeeDao, EmployeeRepository employeeRepository) {
        this.employeeDao = employeeDao;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void findEmployeeByExistId() {
        assertThrows(
                EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 1"));
        assertThrows(
                EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 2"));
        assertThrows(
                EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 3"));
        assertThrows(
                EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 4"));
    }

    @Test
    void shouldThrowEmployeeNotFoundException() throws EmployeeNotFoundException {
        assertEquals(employeeRepository.findByUid(VICTOR_UID).get(), employeeDao.findEmployeeById(VICTOR_UID));
        assertEquals(employeeRepository.findByUid(OLEG_UID).get(), employeeDao.findEmployeeById(OLEG_UID));
        assertEquals(employeeRepository.findByUid(KARL_UID).get(), employeeDao.findEmployeeById(KARL_UID));
        assertEquals(employeeRepository.findByUid(ANNA_UID).get(), employeeDao.findEmployeeById("4"));
    }

    @Test
    void saveEmployeeShouldSaveSuccess() throws EmployeeNotFoundException {
        Employee employee =
                Employee.builder()
                        .uid("5")
                        .position("Junior")
                        .age(19L)
                        .name("Matis")
                        .surname("Matronus")
                        .salary(80_000L)
                        .build();
        employeeDao.save(employee);
        assertEquals(employee, employeeDao.findEmployeeById(employee.getUid()));
    }

    @Test
    void updateEmployeeShouldUpdateSuccess() {
        Employee employee =
                Employee.builder()
                        .uid("4")
                        .position("Manager")
                        .age(44L)
                        .name("Anna")
                        .surname("Volatilisina")
                        .salary(80_000L)
                        .build();
        employeeDao.update(employee);
        assertEquals(employee, employeeRepository.findByUid(employee.getUid()).get());
    }

    @Test
    void deleteEmployeeShouldDecreaseTheCountOfEmployees() {
        employeeDao.delete(VICTOR_UID);
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById(VICTOR_UID));
        assertEquals(COUNT_OF_EMPLOYEES - 1, employeeRepository.findAll().size());
    }
}
