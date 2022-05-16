package com.example.employeesoap.service;

import com.example.employeesoap.dto.EmployeeDto;

import static com.example.employeesoap.type.Status.*;

import lombok.Getter;

import java.util.List;
import java.util.Map;

//todo ожидаю билдер по содержанию это не похоже на билдер
// done
@Getter
public class EmployeeErrorDtoInitializer {
    private static final String MESSAGE = "cannot be empty";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String POSITION = "position";
    public static final String GRADE = "grade";
    public static final String DESCRIPTION = "description";
    public static final String AGE = "age";
    public static final String SALARY = "salary";
    public static final String TASKS_UID = "tasksUID";
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
