package com.example.employeesoap.service;

import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.type.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.example.employeesoap.type.Position.*;

@Service
@RequiredArgsConstructor
public class ValidatorFieldsService {

    public static final String POSITION = "position";

    public EmployeeDto validCheck(Employee employee) {
        EmployeeChecker employeeChecker = new EmployeeChecker();
        EmployeeErrorDtoBuilder employeeMessageError = new EmployeeErrorDtoBuilder();

            employeeMessageError.addFieldsEmpty(employeeChecker.checkRequiredFields(employee));

            Position position = getDefine(employee.getPosition());

        if (position != INDEFINITE) {
            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkAge(position, employee.getAge()));

            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkSalary(position, employee.getSalary()));

            employeeMessageError.addIllegalArgumentMessage(
                    employeeChecker.checkAdmissibleTaskCount(position, (long) employee.getTasks().size()));
        } else {
            employeeMessageError.addIllegalArgumentMessage(new HashMap<String, String>(){{
                put(POSITION, INDEFINITE.getPosition());
            }});
        }
        if (employeeMessageError.hasErrors()) {
            return employeeMessageError.getEmployeeErrorDto();
        }
        return null;
    }
}
