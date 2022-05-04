package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;
import com.example.employeesoap.repository.EmployeeRepository;
import org.springframework.scheduling.annotation.Scheduled;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) { //todo lombok
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("employee with id : " + id + " doesn't exist");
        }
    }
}
