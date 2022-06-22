package com.example.employeesoap.dao;

import com.example.employeesoap.IntegrationTest;
import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exception.EmployeeNotFoundException;
import com.example.employeesoap.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoImplTest extends IntegrationTest {
    private final EmployeeDao employeeDao;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeDaoImplTest(EmployeeDao employeeDao, EmployeeRepository employeeRepository) {
        this.employeeDao = employeeDao;
        this.employeeRepository = employeeRepository;
    }

    @Test
    void findEmployeeByExistIdTest() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 1"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 2"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 3"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("not exist 4"));
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionTest() throws EmployeeNotFoundException {
        assertEquals(employeeRepository.findByUid("1").get(), employeeDao.findEmployeeById("1"));
        assertEquals(employeeRepository.findByUid("2").get(), employeeDao.findEmployeeById("2"));
        assertEquals(employeeRepository.findByUid("3").get(), employeeDao.findEmployeeById("3"));
        assertEquals(employeeRepository.findByUid("4").get(), employeeDao.findEmployeeById("4"));
    }

    @Test
    void shouldSaveEmployeeTest() throws EmployeeNotFoundException {
        Employee employee = Employee.builder()
                .uid("5")
                .position("Junior")
                .age(19L)
                .name("Matis")
                .surname("Matronus")
                .salary(80_000L)
                .build();
        employeeDao.save(employee);
        assertEquals(employee, employeeDao.findEmployeeById("5"));
    }

    @Test
    void shouldUpdateEmployeeTest() {
        Employee employee = Employee.builder()
                .uid("4")
                .position("Manager")
                .age(44L)
                .name("Anna")
                .surname("Volatilisina")
                .salary(80_000L)
                .build();
        employeeDao.update(employee);
        assertEquals(employee, employeeRepository.findByUid("4").get());
    }

    @Test
    void shouldDeleteEmployeeTest() {
        employeeDao.delete("1");
        assertThrows(EmployeeNotFoundException.class, () -> employeeDao.findEmployeeById("1"));
        assertEquals(3, employeeRepository.findAll().size());
    }
}