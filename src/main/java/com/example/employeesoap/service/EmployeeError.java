package com.example.employeesoap.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class EmployeeError {
    private final StringBuilder messageError;

    public EmployeeError() {
        messageError = new StringBuilder();
    }

    public void addFieldsEmpty(String fields) {
        if (!fields.isEmpty()) {
            messageError.append("Illegal argument. This fields must not empty: ")
                    .append(fields)
                    .append("\n");
        }
    }

    public void addIllegalArgumentMessage(String message) {
        if (!message.isEmpty()) { //todo так себе проверка. это просто проверка value.length == 0. То есть NPE может вылететь
            messageError.append(message)
                    .append("\n");
        }
    }
}
