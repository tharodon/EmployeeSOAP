/* (C)2022 */
package com.example.employeesoap.dao;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exception.EmployeeNotFoundException;
import com.example.employeesoap.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee findEmployeeById(String id) throws EmployeeNotFoundException {
        return employeeRepository
                .findByUid(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @Transactional
    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Transactional
    @Override
    public Employee update(Employee employee) {
        employee.setId(employeeRepository.findByUid(employee.getUid()).orElse(employee).getId());
        employeeRepository.save(employee);
        return employee;
    }

    @Transactional
    @Override
    public void delete(String id) {
        employeeRepository.deleteByUid(id);
    }
}
