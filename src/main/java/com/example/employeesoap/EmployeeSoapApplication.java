package com.example.employeesoap;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class EmployeeSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeSoapApplication.class, args);
    }

}
