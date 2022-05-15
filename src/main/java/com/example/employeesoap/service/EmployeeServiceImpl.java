package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.api.UIDGenerator;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;

import static com.example.employeesoap.type.Status.*;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//todo добавить интерфейс и использовать через интерфейс
// done
@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeService;
    private final ValidatorFieldsService validatorFieldsService;
    private final EmployeeMapper employeeMapper;

    //todo не нравиться завязка на try-catch. переписать лучше
    //todo попробуй сделать через стримом. Так можно сделать меньше кода + если это отдельная логика валидаци его можно ввынести в приватный метод
    // done

    @Override
    public List<EmployeeDto> addEmployees(List<Employee> employees) {
        List<EmployeeDto> response = employees.stream()
                .map(this::validation)
                .map(this::generateId)
                .collect(Collectors.toList());
        List<Employee> legalEmployees = response.stream()
                .filter(employeeDto -> employeeDto.getStatus() == SUCCESS)
                .map(employeeMapper::employeeDtoToEmployee)
                .collect(Collectors.toList());
        employeeService.save(legalEmployees);

        return response;
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
