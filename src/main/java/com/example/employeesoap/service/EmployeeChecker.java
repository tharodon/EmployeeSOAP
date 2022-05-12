package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.type.Positions;
import com.example.employeesoap.exceptions.InvalidPositionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.employeesoap.type.Positions.*;

//todo почему bean ?. Можно же без него
//done
public class EmployeeChecker {

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String POSITION = "position";
    public static final String AGE = "age";
    public static final String SALARY = "salary";
    public static final String GRADE = "grade";
    public static final String DESCRIPTION = "description";
    public static final String TASKS_UID = "tasksUID";

    public Map<String, String> checkSalary(Positions position, Long salary) {
        Map<String, String> response = new HashMap<>();
        if (salary != null &&
                (salary < position.getSalaryMin() || salary > position.getSalaryMax())) {
            //todo можно сократить используя MessageFormat.format
            //done
            response.put(SALARY, String.format
                    ("Invalid %s. Expected: from %d to %d  received: %d",
                            SALARY, position.getSalaryMin(), position.getSalaryMax(), salary));
        }
        return response;
    }

    public Map<String, String> checkAge(Positions position, Long age) {
        Map<String, String> response = new HashMap<>();
        if (age != null && age < position.getMinAge()) {
            //todo можно сократить используя MessageFormat.format
            //done
            response.put(AGE, String.format(
                    "Invalid %s. Expected: from %d received: %d",
                    AGE, position.getMinAge(), age));
        }
        return response;
    }

    //todo волшебные значения. вынести в константу
    //done
    public List<String> checkRequiredFields(Employee employee) throws InvalidPositionException {
        List<String> invalidFields = new ArrayList<>();
        if (employee.getName() == null) {
            invalidFields.add(NAME);
        }
        if (employee.getSurname() == null) {
            invalidFields.add(SURNAME);
        }
        if (employee.getPosition() == null) {
            invalidFields.add(POSITION);
        }
        if (employee.getAge() == null) {
            invalidFields.add(AGE);
        }
        if (employee.getSalary() == null) {
            invalidFields.add(SALARY);
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
            nullableFields.add(GRADE);
        }
        if (employee.getDescription() == null) {
            nullableFields.add(DESCRIPTION);
        }
        return nullableFields;
    }

    //todo волшебные значения. вынести в константу
    //done
    private List<String> requiredFieldsManager(Employee employee) {
        List<String> nullableFields = new ArrayList<>();
        if (employee.getGrade() == null) {
            nullableFields.add(GRADE);
        }
        return nullableFields;
    }

    public Map<String, String> checkAdmissibleTaskCount(Positions position, Long countTasks) {
        Map<String, String> response = new HashMap<>();
        if (countTasks > position.getCountTasksMax()) {
            //todo можно сократить используя MessageFormat.format
            //done
            response.put(TASKS_UID, String.format(
                    "Invalid count task. Max count for position %s: %d received: %d",
                    position.getPosition(), position.getCountTasksMax(), countTasks));
        }
        return response;
    }
}
