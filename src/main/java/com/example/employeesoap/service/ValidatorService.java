package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.enums.Positions;
import com.example.employeesoap.exceptions.IllegalAgeException;
import com.example.employeesoap.exceptions.IllegalSalaryException;
import com.example.employeesoap.exceptions.InvalidFieldsException;

public class ValidatorService {
    public void validCheck(Employee employee) {
        checkSalary(Positions.getDefine(employee.getPosition()), employee.getSalary());
        if (checkRequiredFields(employee)){
            throw new InvalidFieldsException();
        }else if (!checkAge(employee.getPosition(), employee.getAge())) {
            throw new IllegalAgeException();
        }
    }

    private void checkSalary(Positions position, Long salary) {
        if (!(salary >= position.getSalaryMin() && salary <= position.getSalaryMax())){
            throw new IllegalSalaryException("Illegal salary. expected: from "
                    + position.getSalaryMin()
                    + ", to "
                    + position.getSalaryMax()
                    + " received: " + salary);
        }
    }

    private boolean checkAge(String position, Long age) {
        if (Positions.JUNIOR.getPosition().equals(position)) {
            return age >= 18;
        } else if (Positions.MIDDLE.getPosition().equals(position)
                || Positions.MANAGER.getPosition().equals(position)) {
            return age >= 21;
        } else if (Positions.SENIOR.getPosition().equals(position)) {
            return age >= 28;
        }
        return false;
    }

    private boolean checkRequiredFields(Employee employee) {
        return employee.getAge() == null || employee.getName() == null || employee.getSurname() == null
                || employee.getPosition() == null || employee.getSalary() == null
                || (employee.getPosition().equals(Positions.SENIOR.getPosition())
                && (employee.getDescription() == null || employee.getGrade() == null));
    }
}
