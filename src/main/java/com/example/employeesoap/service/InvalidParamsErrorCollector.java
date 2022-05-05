package com.example.employeesoap.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Getter
public class InvalidParamsErrorCollector { //todo Название сбивает. Лучше EmployeeError
    private final StringBuilder trace; //todo название messageError


    public InvalidParamsErrorCollector() {
        trace = new StringBuilder();
    }

    public void addFieldsEmpty(String fields){
        if (!fields.isEmpty()) {
            trace.append("Illegal argument. This fields must not empty: ")
                    .append(fields)
                    .append("\n");
        }
    }

    public void addIllegalArgumentMessage(String message){
        if (!message.isEmpty()) {
            trace.append(message)
                    .append("\n");
        }
    }
}
