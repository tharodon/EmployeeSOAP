package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapperToFromEmployeeServiceImpl implements MapperToFromEmployeeService {

    private final ValidatorFieldsService validatorService;

    @Override
    public Employee fromEmployeeDto(EmployeeDto dto) {
        Employee employee = new Employee().toBuilder()
                .id(dto.getId())
                .name(dto.getName())
                .surname(dto.getSurname())
                .position(dto.getPosition())
                .grade(dto.getGrade())
                .description(dto.getDescription())
                .age(dto.getAge())
                .salary(dto.getSalary())
                .build();
        validatorService.validCheck(employee);
        return employee;
    }

    @Override
    public EmployeeResponse getResponseFromEmployee(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setEmployeeDto(convertToDto(employee));
        return response;
    }

    private EmployeeDto convertToDto(Employee employee) {
        /*
        todo на всякий)) можно еще так писать билдер
            EmployeeDto.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .surname(employee.getSurname())
                    .position(employee.getPosition())
                    .grade(employee.getGrade())
                    .description(employee.getDescription())
                    .age(employee.getAge())
                    .salary(employee.getSalary())
                    .build();
         */
        return new EmployeeDto()
                .toBuilder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .position(employee.getPosition())
                .grade(employee.getGrade())
                .description(employee.getDescription())
                .age(employee.getAge())
                .salary(employee.getSalary())
                .build();
    }

}
