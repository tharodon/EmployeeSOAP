package com.example.employeesoap.config;

import com.example.employeesoap.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class CronTabConfig { //todo название не говорит, что делает класс + почему Configuration

    @Autowired
    EmployeeRepository employeeRepository; //todo private ?

    @Scheduled(cron = "1 * * * * *")    //todo хранить в properties ?
    public void cron(){
        employeeRepository.deleteByMaxId();
    } //todo то есть будет удалять новые записи, а не старые
}
