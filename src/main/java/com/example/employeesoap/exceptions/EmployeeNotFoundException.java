package com.example.employeesoap.exceptions;

public class EmployeeNotFoundException extends RuntimeException { //todo смотреть todo в InvalidPositionException

    public EmployeeNotFoundException() {
        super("EmployeeNotFound");
    }
}
