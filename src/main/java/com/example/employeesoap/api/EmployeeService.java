package com.example.employeesoap.api;

import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.EmployeeNotFoundException;

import java.util.List;

//todo перенеси все интерфейсы в отдельный пакет api // done
public interface EmployeeService {
    //todo общие замечание. делай по чаще reformat code // done
    Employee findEmployeeById(Long id) throws EmployeeNotFoundException;

    void save(List<Employee> employees);

    Employee update(Employee employee);

    void delete(Long id);
}
