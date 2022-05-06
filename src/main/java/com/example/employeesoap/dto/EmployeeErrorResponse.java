package com.example.employeesoap.dto;

import com.example.employeesoap.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class EmployeeErrorResponse {
    private final Map<Employee, String> invalidEmployeesAndTraces = new HashMap<>();

    public void addTrace(Employee employee, String trace) {
        invalidEmployeesAndTraces.put(employee, trace);
    }

    public boolean hasError() {
        return invalidEmployeesAndTraces.size() > 0;
    }
}
