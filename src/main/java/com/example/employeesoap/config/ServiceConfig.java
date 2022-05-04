package com.example.employeesoap.config;

import com.example.employeesoap.repository.EmployeeRepository;
import com.example.employeesoap.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ServiceConfig {

    private final EmployeeRepository employeeRepository; //todo разнятся стили вызова бина в одном классе Autowired в другом через конструктор

    //todo пока вообще не понимаю зачем это нужно

    @Bean
    MapperService getMapperService(){
        return new MapperServiceImpl(new ValidatorService());
    }

    @Bean
    EmployeeService getEmployeeService(){
        return new EmployeeServiceImpl(employeeRepository);
    }
}
