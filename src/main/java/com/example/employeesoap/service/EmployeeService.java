package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.api.EmployeeMapper;
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
public class EmployeeService { //todo добавить интерфейс и использовать через интерфейс

    private final EmployeeDao employeeService;
    private final ValidatorFieldsService validatorFieldsService;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> addEmployees(List<Employee> employees) {
        List<EmployeeDto> response = employees
                .stream()
                .map(this::validation)
                .collect(Collectors.toList());
        employeeService.save(employees
                        .stream()
                        .filter(employee -> checkEmployeeStatus(employees.indexOf(employee), response))
                        .collect(Collectors.toList()));
        return response;
    }

    public EmployeeDto updateEmployee(Employee employee) {
        EmployeeDto response = validatorFieldsService.validCheck(employee);
        if (response == null) {
            employeeService.update(employee);
            return employeeMapper.employeeToEmployeeDto(employee);
        }
        return response;
    }

    public void deleteEmployee(Long id) {
        employeeService.delete(id);
    }

    @SneakyThrows
    public EmployeeDto getEmployeeById(Long id) {
        return employeeMapper.employeeToEmployeeDto(employeeService.findEmployeeById(id));

    }

    private EmployeeDto validation(Employee employee) {
        EmployeeDto result = validatorFieldsService.validCheck(employee);
        return result == null ? employeeMapper.employeeToEmployeeDto(employee) : result;
    }

    private boolean checkEmployeeStatus(Integer index, List<EmployeeDto> invalidEmployees) {
        return invalidEmployees.get(index).getStatus() == SUCCESS;
    }

}
