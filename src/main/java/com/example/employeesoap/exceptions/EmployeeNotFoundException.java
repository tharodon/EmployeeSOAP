package com.example.employeesoap.exceptions;
//todo название пакета в единственном числе

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(Long id) {
        super("Employee with id: " + id + " not found");
    }
}
