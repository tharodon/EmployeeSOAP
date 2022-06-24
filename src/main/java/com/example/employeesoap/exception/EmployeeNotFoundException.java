/* (C)2022 */
package com.example.employeesoap.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(String id) {
        super("Employee with id: " + id + " not found");
    }
}
