package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.enums.Positions;
import com.example.employeesoap.exceptions.InvalidPositionException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.employeesoap.enums.Positions.*;

@Service
@RequiredArgsConstructor
public class ValidatorFieldsService {

    public void validCheck(Employee employee) throws InvalidPositionException {
        EmployeeMessageError employeeMessageError = new EmployeeMessageError();
        employeeMessageError.addFieldsEmpty(checkRequiredFields(employee));

        employeeMessageError.addIllegalArgumentMessage(
                checkSalary(Positions.getDefine(employee.getPosition()), employee.getSalary()));

        employeeMessageError.addFieldsEmpty(checkAge(getDefine(employee.getPosition()), employee.getAge()));

        employeeMessageError.addIllegalArgumentMessage(checkAdmissibleTaskCount(
                getDefine(employee.getPosition()), (long) employee.getTasks().size()));

        if (employeeMessageError.getMessageError().length() > 0) {
            throw new IllegalArgumentException(employeeMessageError.getMessageError().toString());
        }
    }

    private String checkSalary(Positions position, Long salary) {
        if (salary != null &&
                (salary < position.getSalaryMin() || salary > position.getSalaryMax())) {
            return "Illegal salary. Expected: from "
                    + position.getSalaryMin()
                    + ", to "
                    + position.getSalaryMax()
                    + " received: " + salary + "\n";
        }
        return "";
    }

    private String checkAge(Positions position, Long age) {
        if (age != null && age < position.getMinAge()) {
            return "Invalid age. Expected: from "
                    + position.getMinAge()
                    + " received: " + age;
        }
        return "";
    }

    private String checkRequiredFields(Employee employee) throws InvalidPositionException {
        StringBuilder trace = new StringBuilder();
        if (employee.getName() == null) {
            trace.append("name ");
        }
        if (employee.getSurname() == null) {
            trace.append("surname ");
        }
        if (employee.getPosition() == null) {
            trace.append("position ");
        }
        if (employee.getAge() == null) {
            trace.append("age ");
        }
        if (employee.getSalary() == null) {
            trace.append("salary ");
        }
        if (getDefine(employee.getPosition()) == SENIOR) {
            trace.append(requiredFieldsSenior(employee));
        } else if (getDefine(employee.getPosition()) == MANAGER) {
            trace.append(requiredFieldsManager(employee));
        }
        return trace.toString();
    }

    private String requiredFieldsSenior(Employee employee) {
        StringBuilder trace = new StringBuilder();
        if (employee.getGrade() == null) {
            trace.append("grade ");
        }
        if (employee.getDescription() == null) {
            trace.append("description ");
        }
        return trace.toString();
    }

    private String requiredFieldsManager(Employee employee) {
        StringBuilder trace = new StringBuilder();
        if (employee.getGrade() == null) {
            trace.append("grade ");
        }
        return trace.toString();
    }

    private String checkAdmissibleTaskCount(Positions position, Long countTasks) {
        if (countTasks > position.getCountTasksMax()) {
            return "Invalid count task. Max count for position "
                    + position.getPosition()
                    + ": "
                    + position.getCountTasksMax()
                    + " received: " + countTasks;
        }
        return "";
    }
}
