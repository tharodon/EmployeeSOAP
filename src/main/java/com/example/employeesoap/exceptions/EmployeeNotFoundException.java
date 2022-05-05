package com.example.employeesoap.exceptions;

public class EmployeeNotFoundException extends Exception { //todo сделай от Exception //done

    public EmployeeNotFoundException(Long id) {
        super("Employee with id: " + id + " not found");
    }
}
