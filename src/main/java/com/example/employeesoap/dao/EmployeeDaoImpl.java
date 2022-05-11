package com.example.employeesoap.dao;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.api.EmployeeMapper;
import com.example.employeesoap.dto.EmployeeDto;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;
import com.example.employeesoap.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeDaoImpl implements EmployeeDao {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDto findEmployeeById(Long id) throws EmployeeNotFoundException {
        return employeeMapper.employeeToEmployeeDto(employeeRepository
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
        return employeeMapper.employeeToEmployeeDto(employee);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }
}
