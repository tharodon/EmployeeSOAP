package com.example.employeesoap.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class EmployeeError { //todo Название сбивает. Лучше EmployeeError // done
    private final StringBuilder messageError; //todo название messageError // done


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
        if (!message.isEmpty()) {
            messageError.append(message)
                    .append("\n");
        }
    }
}
