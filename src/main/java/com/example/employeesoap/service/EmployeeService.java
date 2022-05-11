package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.dto.EmployeeErrorResponse;
import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
//todo не очень название. EmployeeDao. + если это dao то вынести в отдельный пакет dao
//done выяснилось, что не этот класс dao, а другой
public class EmployeeService {

    private final EmployeeDao employeeService;
    private final ValidatorFieldsService validatorFieldsService;

    public EmployeeErrorResponse addEmployees(List<Employee> employees){
        EmployeeErrorResponse employeeErrorResponse = new EmployeeErrorResponse();
        for (int i = 0; i < employees.size(); i++){
            //todo не нравиться завязка на try-catch. переписать лучше
            try {
                validatorFieldsService.validCheck(employees.get(i));
            } catch (InvalidPositionException | IllegalArgumentException e) { //todo почему такое "ИЛИ" ?
                log.debug("Invalid employee parameter: {}, traces: {}", employees.get(i), e.getMessage());
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
            employeeService.update(employee);
        } catch (InvalidPositionException | IllegalArgumentException e) { //todo почему такое "ИЛИ" ?
            log.debug("Invalid employee parameter: {}, traces: {}", employee, e.getMessage());
            employeeErrorResponse.addTrace(employee, e.getMessage());
        }
        return employeeErrorResponse;
    }

    public void deleteEmployee(Long id){
        employeeService.delete(id);
    }

    @SneakyThrows
    public EmployeeDto getEmployeeById(Long id) {
        return employeeService.findEmployeeById(id);
    }

}
