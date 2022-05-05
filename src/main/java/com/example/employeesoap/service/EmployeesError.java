package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
public class EmployeesError {
    private final StringBuilder messageError =  new StringBuilder();
    private final Map<Employee, String> invalidEmployees = new HashMap<>();

    public void flushEmployee(Employee employee) {
        invalidEmployees.put(employee, messageError.toString());
        messageError.setLength(0);
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
