package com.example.employeesoap.config;

import com.example.employeesoap.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CronTabConfig {

    @Autowired
    EmployeeRepository employeeRepository;

    @Scheduled(cron = "1 * * * * *")
    public void cron(){
        employeeRepository.deleteByMaxId();
    }
}
