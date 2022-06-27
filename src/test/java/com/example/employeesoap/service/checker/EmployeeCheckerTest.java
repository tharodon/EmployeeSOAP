package com.example.employeesoap.service.checker;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.Position.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.employeesoap.entity.Employee;
import org.junit.jupiter.api.Test;

class EmployeeCheckerTest {

    private final EmployeeChecker employeeChecker = new EmployeeChecker();

    @Test
    void checkLegalSalaryJuniorShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkSalary(JUNIOR, 50_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(JUNIOR, 70_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(JUNIOR, 55_000L).isEmpty());
    }

    @Test
    void checkLegalSalaryMiddleShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkSalary(MIDDLE, 90_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MIDDLE, 210_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MIDDLE, 115_000L).isEmpty());
    }

    @Test
    void checkLegalSalarySeniorShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkSalary(SENIOR, 210_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(SENIOR, 450_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(SENIOR, 355_000L).isEmpty());
    }

    @Test
    void checkLegalSalaryManagerShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkSalary(MANAGER, 70_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MANAGER, 150_000L).isEmpty());
        assertTrue(employeeChecker.checkSalary(MANAGER, 120_000L).isEmpty());
    }

    @Test
    void checkIllegalSalaryJuniorShouldGetErrorResponse() {
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
    void checkIllegalSalaryMiddleShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkSalary(MIDDLE, 89_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MIDDLE, 211_000L).isEmpty());
    }

    @Test
    void checkIllegalSalarySeniorShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkSalary(SENIOR, 209_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(SENIOR, 451_000L).isEmpty());
    }

    @Test
    void checkIllegalSalaryManagerShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkSalary(MANAGER, 69_000L).isEmpty());
        assertFalse(employeeChecker.checkSalary(MANAGER, 151_000L).isEmpty());
    }

    @Test
    void checkAgeShouldGetEmptyResponse() {
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
    void checkIllegalAgeShouldGetErrorResponse() {
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
    void checkRequiredFieldsValidJuniorEmployeeShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkRequiredFields(getLegalJunior()).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidMiddleEmployeeShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkRequiredFields(getLegalMiddle()).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidManagerEmployeeShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkRequiredFields(getLegalManager()).isEmpty());
    }

    @Test
    void checkRequiredFieldsValidSeniorEmployeeShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkRequiredFields(getLegalSenior()).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableGradeOfSeniorShouldGetErrorResponse() {
        Employee employee = getLegalSenior();
        employee.setGrade(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableDescriptionOfSeniorShouldGetErrorResponse() {
        Employee employee = getLegalSenior();
        employee.setDescription(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableGradeOfManageShouldGetErrorResponse() {
        Employee employee = getLegalManager();
        employee.setGrade(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableAgeShouldGetErrorResponse() {
        Employee employee = getLegalJunior();
        employee.setAge(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableNameShouldGetErrorResponse() {
        Employee employee = getLegalJunior();
        employee.setName(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
        employee.setName("");
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableSurnameShouldGetErrorResponse() {
        Employee employee = getLegalJunior();
        employee.setSurname(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
        employee.setSurname("");
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullableSalaryShouldGetErrorResponse() {
        Employee employee = getLegalJunior();
        employee.setSalary(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkRequiredFieldsNullablePositionShouldGetErrorResponse() {
        Employee employee = getLegalJunior();
        employee.setPosition(null);
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
        employee.setPosition("");
        assertFalse(employeeChecker.checkRequiredFields(employee).isEmpty());
    }

    @Test
    void checkAdmissibleTaskLegalCountJuniorShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 3L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 0L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 1L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskLegalCountMiddleShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 10L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 9L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 0L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskLegalCountSeniorShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 15L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 14L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 9L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 0L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(SENIOR, 10L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskLegalCountManagerShouldGetEmptyResponse() {
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 15L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 14L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 9L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 0L).isEmpty());
        assertTrue(employeeChecker.checkAdmissibleTaskCount(MANAGER, 10L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskIllegalCountJuniorShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 17L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(JUNIOR, -1L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(JUNIOR, 187L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskIllegalCountMiddleShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 17L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 27L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MIDDLE, 11L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskIllegalCountManagerShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MANAGER, 25L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MANAGER, 27L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(MANAGER, 18L).isEmpty());
    }

    @Test
    void checkAdmissibleTaskIllegalCountSeniorShouldGetErrorResponse() {
        assertFalse(employeeChecker.checkAdmissibleTaskCount(SENIOR, 25L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(SENIOR, 27L).isEmpty());
        assertFalse(employeeChecker.checkAdmissibleTaskCount(SENIOR, 18L).isEmpty());
    }
}
