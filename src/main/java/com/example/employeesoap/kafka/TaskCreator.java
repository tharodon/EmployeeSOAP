package com.example.employeesoap.kafka;

import com.example.employeesoap.entity.Employee;

public interface TaskCreator {
    void createTask(Employee employee);
}
