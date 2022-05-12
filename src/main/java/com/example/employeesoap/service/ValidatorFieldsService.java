package com.example.employeesoap.service;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.example.employeesoap.enums.Positions.*;

@Service
@RequiredArgsConstructor
public class ValidatorFieldsService {

    private final EmployeeChecker employeeChecker;

    public EmployeeDto validCheck(Employee employee) {
        EmployeeErrorDtoBuilder employeeMessageError = new EmployeeErrorDtoBuilder();

        try {
            employeeMessageError.addFieldsEmpty(employeeChecker.checkRequiredFields(employee));

            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkAge(getDefine(employee.getPosition()), employee.getAge()));

            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkSalary(getDefine(employee.getPosition()), employee.getSalary()));
            employeeChecker.checkAdmissibleTaskCount(getDefine(employee.getPosition()), (long) employee.getTasks().size());
        } catch (InvalidPositionException e) {
            employeeMessageError.addIllegalArgumentMessage(
                    new HashMap<String, String>(){{put("position", e.getMessage());}}); //todo волшебные значения. вынести в константу
        }
        if (employeeMessageError.hasErrors()) {
            return employeeMessageError.getEmployeeErrorDto();
        }
        return null;
    }
}
