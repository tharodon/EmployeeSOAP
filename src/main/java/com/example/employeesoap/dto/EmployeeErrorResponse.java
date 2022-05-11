package com.example.employeesoap.dto;

import com.example.employeesoap.entity.Employee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@NoArgsConstructor
public class EmployeeErrorResponse {
    private static int EMPTY = 0;
    private final Map<Employee, String> invalidEmployeesAndTraces = new HashMap<>();

    public void addTrace(Employee employee, String trace) {
        invalidEmployeesAndTraces.put(employee, trace);
    }

    //todo волшебное значение. Вынести в константу
    //done
    public boolean hasError() {
        return invalidEmployeesAndTraces.size() > EMPTY;
    }
}
