package com.example.employeesoap.exceptions;

public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException() {
        super("EmployeeNotFound");
    }
}
