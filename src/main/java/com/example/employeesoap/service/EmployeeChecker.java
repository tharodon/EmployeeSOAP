package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.type.Position;

import java.text.MessageFormat;
import java.util.*;

import static com.example.employeesoap.type.Position.*;

//todo переносы
// done
public class EmployeeChecker {

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String POSITION = "position";
    public static final String AGE = "age";
    public static final String SALARY = "salary";
    public static final String GRADE = "grade";
    public static final String DESCRIPTION = "description";
    public static final String TASKS_UID = "tasksUID";
    public static final String FILENAME = "messages_US";
    public static final String AGE_BUNDLE_KEY = "invalid.age";
    public static final String SALARY_BUNDLE_KEY = "invalid.salary";
    public static final String TASKS_BUNDLE_KEY = "invalid.tasks";

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(FILENAME, new Locale("US", "US"));

    public Map<String, String> checkSalary(Position position, Long salary) {
        Map<String, String> response = new HashMap<>();
        if (salary != null &&
                (salary < position.getSalaryMin() || salary > position.getSalaryMax())) {
            response.put(SALARY, MessageFormat.format(
                    resourceBundle.getString(SALARY_BUNDLE_KEY),
                    SALARY,
                    position.getSalaryMin(),
                    position.getSalaryMax(),
                    salary));
        }
        return response;
    }

    public Map<String, String> checkAge(Position position, Long age) {
        Map<String, String> response = new HashMap<>();
        if (age != null && age < position.getMinAge()) {
            response.put(AGE, MessageFormat.format(
                    resourceBundle.getString(AGE_BUNDLE_KEY),
                    AGE,
                    position.getMinAge(),
                    age));
        }
        return response;
    }

    public List<String> checkRequiredFields(Employee employee) {
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

    public Map<String, String> checkAdmissibleTaskCount(Position position, Long countTasks) {
        Map<String, String> response = new HashMap<>();
        if (countTasks > position.getCountTasksMax()) {
            response.put(TASKS_UID, MessageFormat.format(
                    resourceBundle.getString(TASKS_BUNDLE_KEY),
                    position.getPosition(),
                    position.getCountTasksMax(),
                    countTasks));
        }
        return response;
    }

    //todo приватные методы должны быть снизу. я поправил. Это на будущее
    // done принял
    private List<String> requiredFieldsManager(Employee employee) {
        List<String> nullableFields = new ArrayList<>();
        if (employee.getGrade() == null) {
            nullableFields.add(GRADE);
        }
        return nullableFields;
    }
}
