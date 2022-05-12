package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeService { //todo добавить интерфейс и использовать через интерфейс

    private final EmployeeDao employeeService;
    private final ValidatorFieldsService validatorFieldsService;
    private final EmployeeMapper employeeMapper;

    //todo не нравиться завязка на try-catch. переписать лучше
    //done
    public List<EmployeeDto> addEmployees(List<Employee> employees) {
        List<EmployeeDto> response = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) { //todo попробуй сделать через стримом. Так можно сделать меньше кода + если это отдельная логика валидаци его можно ввынести в приватный метод
            EmployeeDto invalidEmployee = validatorFieldsService.validCheck(employees.get(i));
            if (invalidEmployee != null) {
                response.add(invalidEmployee);
                employees.remove(i);
                i--;
            } else {
                response.add(employeeMapper.employeeToEmployeeDto(employees.get(i)));
            }
        }
        employeeService.save(employees);
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

}
