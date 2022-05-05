package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.enums.Positions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.employeesoap.enums.Positions.*;

@Service
@RequiredArgsConstructor
public class ValidatorFieldsService {

    private final InvalidParamsErrorCollector collector; //todo название пиши лучше полностью

    public void validCheck(Employee employee) {
        collector.addFieldsEmpty(checkRequiredFields(employee));
        collector.addIllegalArgumentMessage(
                checkSalary(Positions.getDefine(employee.getPosition()), employee.getSalary()));
        collector.addFieldsEmpty(checkAge(Positions.getDefine(employee.getPosition()), employee.getAge()));
        if (collector.getTrace().length() > 0){
            throw new IllegalArgumentException(collector.getTrace().toString());
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
        if (age != null && age < position.getMinAge()){
            return "Invalid age. Expected: from "
                    + position.getMinAge()
                    + " receiced: " + age;
        }
        return "";
    }

    private String checkRequiredFields(Employee employee) {
        StringBuilder trace = new StringBuilder();
        if (employee.getName() == null){
            trace.append("name ");
        }
        if (employee.getSurname() == null){
            trace.append("surname ");
        }
        if (employee.getPosition() == null){
            trace.append("position ");
        }
        if (employee.getAge() == null){
            trace.append("age ");
        }
        if (employee.getSalary() == null){
            trace.append("salary ");
        }
        if (getDefine(employee.getPosition()) == SENIOR){
            trace.append(requiredFieldsSenior(employee));
        }else if (getDefine(employee.getPosition()) == MANAGER){
            trace.append(requiredFieldsManager(employee));
        }
        return trace.toString();
    }

    private String requiredFieldsSenior(Employee employee){
        StringBuilder trace = new StringBuilder();
        if (employee.getGrade() == null){
            trace.append("grade ");
        }
        if (employee.getDescription() == null){
            trace.append("description ");
        }
        return trace.toString();
    }

    private String requiredFieldsManager(Employee employee){
        StringBuilder trace = new StringBuilder();
        if (employee.getGrade() == null){
            trace.append("grade ");
        }
        return trace.toString();
    }
}
