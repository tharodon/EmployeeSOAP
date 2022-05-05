package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;
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

    public void addEmployees(List<Employee> employees){
        try {
            validatorFieldsService.validCheck(employees);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        employeeService.save(employees);
    }

    @SneakyThrows
    public void updateEmployee(Employee employee) {
        validatorFieldsService.validCheck(Collections.singletonList(employee));
        employeeService.save(Collections.singletonList(employee));
    }

    @SneakyThrows
    public void deleteEmployee(Long id){
        employeeService.delete(employeeService.findEmployeeById(id));
    }

    @SneakyThrows
    public Employee getEmployeeById(Long id) {
        return employeeService.findEmployeeById(id);
    }

}
