package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.enums.Positions;
import com.example.employeesoap.exceptions.InvalidPositionException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.employeesoap.enums.Positions.*;

@Service
public class EmployeeChecker {

    //todo можно вынести в другой класс EmployeeChecker все проверки
    //done
    public Map<String, String> checkSalary(Positions position, Long salary) {
        Map<String, String> response = new HashMap<>();
        if (salary != null &&
                (salary < position.getSalaryMin() || salary > position.getSalaryMax())) {
            response.put("salary", "Illegal salary. Expected: from "
                    + position.getSalaryMin()
                    + ", to "
                    + position.getSalaryMax()
                    + " received: " + salary);
        }
        return response;
    }

    public Map<String, String> checkAge(Positions position, Long age) {
        Map<String, String> response = new HashMap<>();
        if (age != null && age < position.getMinAge()) {
            response.put("age", "Invalid age. Expected: from "
                    + position.getMinAge()
                    + " received: " + age);
        }
        return response;
    }

    public List<String> checkRequiredFields(Employee employee) throws InvalidPositionException {
        List<String> invalidFields = new ArrayList<>();
        if (employee.getName() == null) {
            invalidFields.add("name");
        }
        if (employee.getSurname() == null) {
            invalidFields.add("surname");
        }
        if (employee.getPosition() == null) {
            invalidFields.add("position");
        }
        if (employee.getAge() == null) {
            invalidFields.add("age");
        }
        if (employee.getSalary() == null) {
            invalidFields.add("salary");
        }
        if (getDefine(employee.getPosition()) == SENIOR) {
            invalidFields.addAll(requiredFieldsSenior(employee));
        } else if (getDefine(employee.getPosition()) == MANAGER) {
            invalidFields.addAll(requiredFieldsManager(employee));
        }
        return invalidFields;
    }

    private List<String> requiredFieldsSenior(Employee employee) {
        List<String> nullableFields = new ArrayList<>();
        if (employee.getGrade() == null) {
            nullableFields.add("grade");
        }
        if (employee.getDescription() == null) {
            nullableFields.add("description");
        }
        return nullableFields;
    }

    private List<String> requiredFieldsManager(Employee employee) {
        List<String> nullableFields = new ArrayList<>();
        if (employee.getGrade() == null) {
            nullableFields.add("grade");
        }
        return nullableFields;
    }

    public String checkAdmissibleTaskCount(Positions position, Long countTasks) {
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
