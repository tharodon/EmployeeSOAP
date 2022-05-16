package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.api.UIDGenerator;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

import static com.example.employeesoap.type.Status.*;

import com.example.employeesoap.kafka.TaskCreator;
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
                .peek(employee -> employee.setId(response.get(employees.indexOf(employee)).getId()))
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
            UIDGenerator uidGenerator = new UIDGeneratorRandom();
            employeeDto.setId(uidGenerator.generateUID());
        }
        return employeeDto;
    }

    private EmployeeDto validation(Employee employee) {
        EmployeeDto result = validatorFieldsService.validCheck(employee);
        return result == null ? employeeMapper.employeeToEmployeeDto(employee) : result;
    }

}
