package com.example.employeesoap.exceptions;

public class EmployeeNotFoundException extends RuntimeException { //todo сделай от Exception

    public EmployeeNotFoundException() {
        super("EmployeeNotFound");
    }
}
