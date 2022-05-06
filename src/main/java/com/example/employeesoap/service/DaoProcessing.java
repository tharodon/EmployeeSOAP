package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.dto.EmployeeErrorResponse;
import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DaoProcessing {

    private final EmployeeService employeeService;
    private final ValidatorFieldsService validatorFieldsService;

    public EmployeeErrorResponse addEmployees(List<Employee> employees){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();
        for (int i = 0; i < employees.size(); i++){
            try {
                validatorFieldsService.validCheck(employees.get(i));
            } catch (InvalidPositionException | IllegalArgumentException e) {
                employeeErrorResponse.addTrace(employees.get(i), e.getMessage());
                employees.remove(i);
                i--;
            }
        }
        employeeService.save(employees);
        return employeeErrorResponse;
    }

    public EmployeeErrorResponse updateEmployee(Employee employee) {
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();
        try {
            validatorFieldsService.validCheck(employee);
            employeeService.save(Collections.singletonList(employee));
        } catch (InvalidPositionException | IllegalArgumentException e) {
            employeeErrorResponse.addTrace(employee, e.getMessage());
        }
        return employeeErrorResponse;
    }

    public void deleteEmployee(Long id){
        employeeService.delete(id);
    }

    @SneakyThrows
    public Employee getEmployeeById(Long id) {
        return employeeService.findEmployeeById(id);
    }

}
