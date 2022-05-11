package com.example.employeesoap.service;

import com.example.employeesoap.dto.EmployeeDto;

import static com.example.employeesoap.enums.Status.*;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class EmployeeErrorDtoBuilder {
    private static final String MESSAGE = "cannot be empty";
    private final EmployeeDto employeeErrorDto;

    public EmployeeErrorDtoBuilder() {
        this.employeeErrorDto = new EmployeeDto();
        employeeErrorDto.setStatus(SUCCESS);
    }

    public void addFieldsEmpty(List<String> incorrectFields) {
        if (!incorrectFields.isEmpty()){
            employeeErrorDto.setStatus(ERROR);
        }
        for (String field : incorrectFields) {
            switch (field) {
                case ("name"): {
                    employeeErrorDto.setName(MESSAGE);
                    break;
                }
                case ("surname"): {
                    employeeErrorDto.setSurname(MESSAGE);
                    break;
                }
                case ("position"): {
                    employeeErrorDto.setPosition(MESSAGE);
                    break;
                }
                case ("grade"): {
                    employeeErrorDto.setGrade(MESSAGE);
                    break;
                }
                case ("description"): {
                    employeeErrorDto.setDescription(MESSAGE);
                    break;
                }
                case ("age"): {
                    employeeErrorDto.setAge(MESSAGE);
                    break;
                }
                case ("salary"): {
                    employeeErrorDto.setSalary(MESSAGE);
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void addIllegalArgumentMessage(Map<String, String> illegalArguments) {
        if (!illegalArguments.isEmpty()){
            employeeErrorDto.setStatus(ERROR);
        }
        for (Map.Entry<String, String> entry : illegalArguments.entrySet()){
            switch (entry.getKey()) {
                case ("name"): {
                    employeeErrorDto.setName(entry.getValue());
                    break;
                }
                case ("surname"): {
                    employeeErrorDto.setSurname(entry.getValue());
                    break;
                }
                case ("position"): {
                    employeeErrorDto.setPosition(entry.getValue());
                    break;
                }
                case ("grade"): {
                    employeeErrorDto.setGrade(entry.getValue());
                    break;
                }
                case ("description"): {
                    employeeErrorDto.setDescription(entry.getValue());
                    break;
                }
                case ("age"): {
                    employeeErrorDto.setAge(entry.getValue());
                    break;
                }
                case ("salary"): {
                    employeeErrorDto.setSalary(entry.getValue());
                    break;
                }
                default:
                    break;
            }
        }
    }

    public boolean hasErrors(){
        return employeeErrorDto.getStatus() == ERROR;
    }
}
