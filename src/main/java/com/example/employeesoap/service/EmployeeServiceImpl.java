package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;
import com.example.employeesoap.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee save(Employee employee) { //todo @Transactional ?
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee update(Employee employee) { //todo @Transactional ?
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public void delete(Long id) { //todo @Transactional ?
        //todo зачем try-catch ?
        try {
            employeeRepository.deleteById(id);
        } catch (Exception e) {
            System.out.println("employee with id : " + id + " doesn't exist");
        }
    }
}
