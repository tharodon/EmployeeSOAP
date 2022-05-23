package com.example.employeesoap.service.validation;

import com.example.employeesoap.api.ValidatorFieldsService;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.service.employee.EmployeeErrorDtoInitializer;
import com.example.employeesoap.service.checker.EmployeeChecker;
import com.example.employeesoap.type.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static com.example.employeesoap.support.ConstantsSupport.*;
import static com.example.employeesoap.type.Position.*;

@Service
@RequiredArgsConstructor
public class ValidatorFieldsServiceImpl implements ValidatorFieldsService {

    @Override
    public EmployeeDto validCheck(Employee employee) {
        EmployeeChecker employeeChecker = new EmployeeChecker();
        EmployeeErrorDtoInitializer employeeMessageError = new EmployeeErrorDtoInitializer();

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
            employeeMessageError.addIllegalArgumentMessage(new HashMap<String, String>() {{
                put(POSITION, INDEFINITE.getPosition());
            }});
        }
        if (employeeMessageError.hasErrors()) {
            return employeeMessageError.getEmployeeErrorDto();
        }
        return null;
    }
}
