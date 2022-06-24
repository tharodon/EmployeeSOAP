package com.example.employeesoap.service.validation;

import static com.example.employeesoap.support.ConstantsSupport.POSITION;
import static com.example.employeesoap.type.Position.INDEFINITE;
import static com.example.employeesoap.type.Position.getDefine;

import com.example.employeesoap.api.ValidatorFieldsService;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.service.checker.EmployeeChecker;
import com.example.employeesoap.service.employee.EmployeeErrorDtoInitializer;
import com.example.employeesoap.type.Position;
import java.util.HashMap;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
                    employeeChecker.checkAdmissibleTaskCount(position, getCountTasks(employee)));
        } else {
            employeeMessageError.addIllegalArgumentMessage(
                    new HashMap<String, String>() {
                        {
                            put(POSITION, INDEFINITE.getPosition());
                        }
                    });
        }
        if (employeeMessageError.hasErrors()) {
            return employeeMessageError.getEmployeeErrorDto();
        }
        return null;
    }

    private long getCountTasks(Employee employee) {
        if (Objects.isNull(employee.getTasks())) {
            return 0L;
        }
        return employee.getTasks().size();
    }
}
