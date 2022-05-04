package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.enums.Positions;
import com.example.employeesoap.exceptions.IllegalAgeException;
import com.example.employeesoap.exceptions.IllegalSalaryException;
import com.example.employeesoap.exceptions.InvalidFieldsException;

public class ValidatorService {//todo лучше добавить Validator чего

    public void validCheck(Employee employee) {
        //todo вылетит NPE)) Проверка обязательных полей вызывается позже, чем проверка зп
        checkSalary(Positions.getDefine(employee.getPosition()), employee.getSalary());
        if (checkRequiredFields(employee)) {
            throw new InvalidFieldsException();
        } else if (!checkAge(employee.getPosition(), employee.getAge())) {
            throw new IllegalAgeException();
        }
    }

    private void checkSalary(Positions position, Long salary) {
        //todo написать условие без инферсии
        if (!(salary >= position.getSalaryMin() && salary <= position.getSalaryMax())) {
            throw new IllegalSalaryException("Illegal salary. expected: from "
                    + position.getSalaryMin()
                    + ", to "
                    + position.getSalaryMax()
                    + " received: " + salary);
        }
    }

    private boolean checkAge(String position, Long age) {
        //todo можно сделать импорт констант и избавиться от Positions. Это облегчит читаемость кода
        if (Positions.JUNIOR.getPosition().equals(position)) {
            return age >= 18; //todo магическое число)) Такие вещи лучше выносить в константу
        } else if (Positions.MIDDLE.getPosition().equals(position)
                || Positions.MANAGER.getPosition().equals(position)) {
            return age >= 21; //todo магическое число)) Такие вещи лучше выносить в константу
        } else if (Positions.SENIOR.getPosition().equals(position)) {
            return age >= 28; //todo магическое число)) Такие вещи лучше выносить в константу
        }
        return false;
    }

    private boolean checkRequiredFields(Employee employee) {
        //todo а если придет "" + есть ощущение что прилетит NPE
        return employee.getAge() == null || employee.getName() == null || employee.getSurname() == null
                || employee.getPosition() == null || employee.getSalary() == null
                || (employee.getPosition().equals(Positions.SENIOR.getPosition())  //todo почему именно SENIOR ?
                && (employee.getDescription() == null || employee.getGrade() == null)); //TODO почему отдельно && ?
    }
}
