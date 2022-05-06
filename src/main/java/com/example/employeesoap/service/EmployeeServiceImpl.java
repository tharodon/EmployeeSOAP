package com.example.employeesoap.service;

import com.example.employeesoap.api.EmployeeService;
import com.example.employeesoap.api.MapperFromEmployeeService;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;
import com.example.employeesoap.api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final MapperFromEmployeeService mapperFromEmployeeService;

    @Override
    public EmployeeDto findEmployeeById(Long id) throws EmployeeNotFoundException {
        return mapperFromEmployeeService.convertFromEmployee(employeeRepository
                .findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id)));
    }

    @Transactional
    @Override
    public void save(List<Employee> employees) {
        employeeRepository.saveAll(employees);
    }

    @Transactional
    @Override
    public EmployeeDto update(Employee employee) {
        employeeRepository.save(employee);
        return mapperFromEmployeeService.convertFromEmployee(employee);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
