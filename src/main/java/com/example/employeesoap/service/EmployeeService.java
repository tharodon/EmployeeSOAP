package com.example.employeesoap.service;

import com.example.employeesoap.entity.Employee;

//todo перенеси все интерфейсы в отдельный пакет api
public interface EmployeeService {
    //todo общие замечание. делай по чаще reformat code
    Employee findEmployeeById(Long id);
    Employee save(Employee employee);
    Employee update(Employee employee);
    void delete(Long id);
}
