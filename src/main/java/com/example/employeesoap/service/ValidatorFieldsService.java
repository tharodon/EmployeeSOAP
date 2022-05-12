package com.example.employeesoap.service;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.example.employeesoap.type.Positions.*;

@Service
@RequiredArgsConstructor
public class ValidatorFieldsService {

    public static final String POSITION = "position";

    public EmployeeDto validCheck(Employee employee) {
        EmployeeChecker employeeChecker = new EmployeeChecker();
        EmployeeErrorDtoBuilder employeeMessageError = new EmployeeErrorDtoBuilder();

        try {
            employeeMessageError.addFieldsEmpty(employeeChecker.checkRequiredFields(employee));

            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkAge(getDefine(employee.getPosition()), employee.getAge()));

            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkSalary(getDefine(employee.getPosition()), employee.getSalary()));
            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkAdmissibleTaskCount(getDefine(employee.getPosition()), (long) employee.getTasks().size()));
        } catch (InvalidPositionException e) {
            employeeMessageError.addIllegalArgumentMessage(
                    //todo волшебные значения. вынести в константу
                    //done
                    new HashMap<String, String>() {{
                        put(POSITION, e.getMessage());
                    }});
        }
        if (employeeMessageError.hasErrors()) {
            return employeeMessageError.getEmployeeErrorDto();
        }
        return null;
    }
}
