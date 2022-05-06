package com.example.employeesoap.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(Long id) {
        super("Employee with id: " + id + " not found");
    }
}
