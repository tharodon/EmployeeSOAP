package com.example.employeesoap.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
public class EmployeeMessageError {
    private final StringBuilder messageError =  new StringBuilder();

    public void addFieldsEmpty(String fields) {
        if (!fields.isEmpty()) {
            messageError.append("Illegal argument. This fields must not empty: ")
                    .append(fields)
                    .append("\n");
        }
    }

    public void addIllegalArgumentMessage(String message) {
        if (!message.isEmpty()) {
            messageError.append(message)
                    .append("\n");
        }
    }
}
