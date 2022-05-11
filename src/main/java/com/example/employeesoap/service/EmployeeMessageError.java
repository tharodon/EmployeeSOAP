package com.example.employeesoap.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

@Getter
public class EmployeeMessageError {
    private final StringBuilder messageError =  new StringBuilder();

    public void addFieldsEmpty(String fields) {
        if (!fields.isEmpty()) { //todo не оч хорошая проверка. По сути это length == 0. + есть вероятность NPE
            messageError.append("Illegal argument. This fields must not empty: ") //todo вынести в константу
                    .append(fields)
                    .append("\n");
        }
    }

    public void addIllegalArgumentMessage(String message) {
        if (!message.isEmpty()) {  //todo не оч хорошая проверка. По сути это length == 0. + есть вероятность NPE
            messageError.append(message)
                    .append("\n");
        }
    }
}
