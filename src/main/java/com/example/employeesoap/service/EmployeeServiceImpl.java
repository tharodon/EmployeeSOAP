package com.example.employeesoap.service;

import com.example.employeesoap.api.*;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

import static com.example.employeesoap.type.Status.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeService;
    private final ValidatorFieldsService validatorFieldsService;
    private final EmployeeMapper employeeMapper;

    private final TaskCreator taskCreator;

    @Override
    public List<EmployeeDto> addEmployees(List<Employee> employees) {
        List<EmployeeDto> response = employees.stream()
                .map(this::validation)
                .map(this::generateId)
                .collect(Collectors.toList());
        employees.stream()
                .filter(employee -> checkSuccessStatus(employees.indexOf(employee), response))
                .peek(employee -> employee.setUid(response.get(employees.indexOf(employee)).getUid()))
                .forEach(taskCreator::createTask);
        return response;
    }

    private boolean checkSuccessStatus(Integer index, List<EmployeeDto> allEmployees) {
        return allEmployees.get(index).getStatus() == SUCCESS;
    }

    @Override
    public EmployeeDto updateEmployee(Employee employee) {
        EmployeeDto response = validatorFieldsService.validCheck(employee);
        if (response == null) {
            employeeService.update(employee);
            return employeeMapper.employeeToEmployeeDto(employee);
        }
        return response;
    }

    @Override
    public void deleteEmployee(String id) {
        employeeService.delete(id);
    }

    @Override
    @SneakyThrows
    public EmployeeDto getEmployeeById(String id) {
        return employeeMapper.employeeToEmployeeDto(employeeService.findEmployeeById(id));

    }

    private EmployeeDto generateId(EmployeeDto employeeDto) {
        if (employeeDto.getStatus() == SUCCESS) {
            UidGeneratorRandom uidGenerator = new UidGeneratorRandom();
            employeeDto.setUid(uidGenerator.generateUID());
        }
        return employeeDto;
    }

    private EmployeeDto validation(Employee employee) {
        EmployeeDto result = validatorFieldsService.validCheck(employee);
        return result == null ? employeeMapper.employeeToEmployeeDto(employee) : result;
    }

}
