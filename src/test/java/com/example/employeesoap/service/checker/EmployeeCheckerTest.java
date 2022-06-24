/* (C)2022 */
package com.example.employeesoap.service.checker;

import static com.example.employeesoap.type.Position.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.employeesoap.entity.Employee;
import org.junit.jupiter.api.Test;

class EmployeeCheckerTest {

    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Test
    void checkLegalSalaryJunior() {
        assertTrue(employeeChecker.checkSalary(JUNIOR, 50_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(JUNIOR, 70_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(JUNIOR, 55_000L).isEmpty());
    }

    @Test
    void checkLegalSalaryMiddle() {
        assertTrue(employeeChecker.checkSalary(MIDDLE, 90_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MIDDLE, 210_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MIDDLE, 115_000L).isEmpty());
    }

    @Test
    void checkLegalSalarySenior() {
        assertTrue(employeeChecker.checkSalary(SENIOR, 210_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(SENIOR, 450_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(SENIOR, 355_000L).isEmpty());
    }

    @Test
    void checkLegalSalaryManager() {
        assertTrue(employeeChecker.checkSalary(MANAGER, 70_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MANAGER, 150_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MANAGER, 120_000L).isEmpty());
    }

    @Test
    void checkIllegalSalaryJunior() {
        assertFalse(employeeChecker.checkSalary(JUNIOR, 49_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(JUNIOR, 71_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MIDDLE, 89_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MIDDLE, 211_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(SENIOR, 209_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(SENIOR, 451_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MANAGER, 69_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MANAGER, 151_000L).isEmpty());
    }

    @Test
    void checkIllegalSalaryMiddle() {
        assertFalse(employeeChecker.checkSalary(MIDDLE, 89_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MIDDLE, 211_000L).isEmpty());
    }

    @Test
    void checkIllegalSalarySenior() {
        assertFalse(employeeChecker.checkSalary(SENIOR, 209_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(SENIOR, 451_000L).isEmpty());
    }

    @Test
    void checkIllegalSalaryManager() {
        assertFalse(employeeChecker.checkSalary(MANAGER, 69_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MANAGER, 151_000L).isEmpty());
    }

    @Test
    void checkAge() {
        assertTrue(employeeChecker.checkAge(JUNIOR, 18L).isEmpty());
        assertTrue(employeeChecker.checkAge(JUNIOR, 23L).isEmpty());
        assertTrue(employeeChecker.checkAge(JUNIOR, 43L).isEmpty());

        assertTrue(employeeChecker.checkAge(MIDDLE, 21L).isEmpty());
        assertTrue(employeeChecker.checkAge(MIDDLE, 22L).isEmpty());
        assertTrue(employeeChecker.checkAge(MIDDLE, 27L).isEmpty());

        assertTrue(employeeChecker.checkAge(SENIOR, 28L).isEmpty());
        assertTrue(employeeChecker.checkAge(SENIOR, 43L).isEmpty());
        assertTrue(employeeChecker.checkAge(SENIOR, 34L).isEmpty());

        assertTrue(employeeChecker.checkAge(MANAGER, 21L).isEmpty());
        assertTrue(employeeChecker.checkAge(MANAGER, 22L).isEmpty());
        assertTrue(employeeChecker.checkAge(MANAGER, 27L).isEmpty());
    }

    @Test
    void checkIllegalAge() {
        assertFalse(employeeChecker.checkAge(JUNIOR, 17L).isEmpty());
        assertFalse(employeeChecker.checkAge(MIDDLE, 20L).isEmpty());
        assertFalse(employeeChecker.checkAge(MIDDLE, 14L).isEmpty());
        assertFalse(employeeChecker.checkAge(SENIOR, 25L).isEmpty());
        assertFalse(employeeChecker.checkAge(SENIOR, 27L).isEmpty());
        assertFalse(employeeChecker.checkAge(SENIOR, 18L).isEmpty());
        assertFalse(employeeChecker.checkAge(MANAGER, 20L).isEmpty());
        assertFalse(employeeChecker.checkAge(MANAGER, 14L).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidJuniorEmployee() {
        Employee employee =
                Employee.builder()
                        .position("Junior")
                        .age(19L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(80_000L)
                        .build();
        assertTrue(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidMiddleEmployee() {
        Employee employee =
                Employee.builder()
                        .position("Middle")
                        .age(25L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(170_000L)
                        .build();
        assertTrue(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidManagerEmployee() {
        Employee employee =
                Employee.builder()
                        .position("Manager")
                        .age(25L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(140_000L)
                        .grade("first")
                        .build();
        assertTrue(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidSeniorEmployee() {
        Employee employee =
                Employee.builder()
                        .position("Senior")
                        .age(31L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(410_000L)
                        .grade("first")
                        .description("test")
                        .build();
        assertTrue(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableGradeOfSenior() {
        Employee employee =
                Employee.builder()
                        .position("Senior")
                        .age(31L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(410_000L)
                        .description("test")
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableDescriptionOfSenior() {
        Employee employee =
                Employee.builder()
                        .position("Senior")
                        .age(31L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(410_000L)
                        .grade("first")
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableGradeOfManager() {
        Employee employee =
                Employee.builder()
                        .position("Manager")
                        .age(25L)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(140_000L)
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableAge() {
        Employee employee =
                Employee.builder()
                        .position("Junior")
                        .age(null)
                        .name("Vasiliy")
                        .surname("Matronus")
                        .salary(80_000L)
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableName() {
        Employee employee =
                Employee.builder()
                        .position("Junior")
                        .age(19L)
                        .name(null)
                        .surname("Matronus")
                        .salary(80_000L)
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
        employee.setName("");
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableSurname() {
        Employee employee =
                Employee.builder()
                        .position("Junior")
                        .age(19L)
                        .name("Vasiliy")
                        .surname(null)
                        .salary(80_000L)
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
        employee.setSurname("");
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullableSalary() {
        Employee employee =
                Employee.builder()
                        .position("Junior")
                        .age(19L)
                        .name("Vasiliy")
                        .surname("Petrov")
                        .salary(null)
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsShouldBeErrorNullablePosition() {
        Employee employee =
                Employee.builder()
                        .position(null)
                        .age(19L)
                        .name("Vasiliy")
                        .surname("Petrov")
                        .salary(80_000L)
                        .build();
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
        employee.setPosition("");
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkAdmissibleTaskCountJunior() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 3L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 0L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 1L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 17L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskCountMiddle() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 10L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 9L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 0L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 17L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 27L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 11L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskCountSenior() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 15L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 14L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 9L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 0L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 10L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(SENIOR, 25L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(SENIOR, 27L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(SENIOR, 18L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskCountManager() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 15L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 14L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 9L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 0L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 10L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MANAGER, 25L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MANAGER, 27L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MANAGER, 18L).isEmpty());
    }
}
