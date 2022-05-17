package com.example.employeesoap.service;

import com.example.employeesoap.dto.EmployeeDto;

import static com.example.employeesoap.support.ConstantsSupport.*;
import static com.example.employeesoap.type.Status.*;

import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class EmployeeErrorDtoInitializer {
    //todo у тебя есть класс c константами используй константы, что в нем(только общие)
    // done
    private static final String MESSAGE = "cannot be empty";
    private final EmployeeDto employeeErrorDto;

    public EmployeeErrorDtoInitializer() {
        this.employeeErrorDto = new EmployeeDto();
        employeeErrorDto.setStatus(SUCCESS);
    }

    public void addFieldsEmpty(List<String> incorrectFields) {
        if (!incorrectFields.isEmpty()) {
            employeeErrorDto.setStatus(ERROR);
        }
        for (String field : incorrectFields) {
            switch (field) {
                case NAME: {
                    employeeErrorDto.setName(MESSAGE);
                    break;
                }
                case SURNAME: {
                    employeeErrorDto.setSurname(MESSAGE);
                    break;
                }
                case POSITION: {
                    employeeErrorDto.setPosition(MESSAGE);
                    break;
                }
                case GRADE: {
                    employeeErrorDto.setGrade(MESSAGE);
                    break;
                }
                case DESCRIPTION: {
                    employeeErrorDto.setDescription(MESSAGE);
                    break;
                }
                case AGE: {
                    employeeErrorDto.setAge(MESSAGE);
                    break;
                }
                case SALARY: {
                    employeeErrorDto.setSalary(MESSAGE);
                    break;
                }
                default:
                    break;
            }
        }
    }

    public void addIllegalArgumentMessage(Map<String, String> illegalArguments) {
        if (!illegalArguments.isEmpty()) {
            employeeErrorDto.setStatus(ERROR);
        }
        for (Map.Entry<String, String> entry : illegalArguments.entrySet()) {
            switch (entry.getKey()) {
                case NAME: {
                    employeeErrorDto.setName(entry.getValue());
                    break;
                }
                case SURNAME: {
                    employeeErrorDto.setSurname(entry.getValue());
                    break;
                }
                case POSITION: {
                    employeeErrorDto.setPosition(entry.getValue());
                    break;
                }
                case GRADE: {
                    employeeErrorDto.setGrade(entry.getValue());
                    break;
                }
                case DESCRIPTION: {
                    employeeErrorDto.setDescription(entry.getValue());
                    break;
                }
                case AGE: {
                    employeeErrorDto.setAge(entry.getValue());
                    break;
                }
                case SALARY: {
                    employeeErrorDto.setSalary(entry.getValue());
                    break;
                }
                case TASKS_UID: {
                    employeeErrorDto.setTasksUID(entry.getValue());
                    break;
                }
                default:
                    break;
            }
        }
    }

    public boolean hasErrors() {
        return employeeErrorDto.getStatus() == ERROR;
    }
}
