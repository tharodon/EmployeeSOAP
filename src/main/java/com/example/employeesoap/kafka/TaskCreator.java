package com.example.employeesoap.kafka;

import com.example.employeesoap.entity.Employee;

public interface TaskCreator { //todo интерфейс в пакет api
    void createTask(Employee employee);
}
