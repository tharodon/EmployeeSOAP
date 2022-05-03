package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import io.spring.guides.gs_producing_web_service.EmployeeDto;
import io.spring.guides.gs_producing_web_service.EmployeeResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MapperServiceImpl implements MapperService{

    private final ValidatorService validatorService;

    @Override
    public Employee convertFromDto(EmployeeDto dto) {
        Employee employee = new Employee()
                .setId(dto.getId())
                .setName(dto.getName())
                .setSurname(dto.getSurname())
                .setPosition(dto.getPosition())
                .setGrade(dto.getGrade())
                .setDescription(dto.getDescription())
                .setAge(dto.getAge())
                .setSalary(dto.getSalary());
        validatorService.validCheck(employee);
        return employee;
    }

    @Override
    public EmployeeResponse getResponse(Employee employee) {
        EmployeeResponse response = new EmployeeResponse();
        response.setEmployeeDto(convertToDto(employee));
        return response;
    }

    private EmployeeDto convertToDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSurname(employee.getSurname());
        dto.setPosition(employee.getPosition());
        dto.setGrade(employee.getGrade());
        dto.setDescription(employee.getDescription());
        dto.setAge(employee.getAge());
        dto.setSalary(employee.getSalary());
        return dto;
    }

}
