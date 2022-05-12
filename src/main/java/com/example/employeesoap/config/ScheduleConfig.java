package com.example.employeesoap.config;

import com.example.employeesoap.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
public class ScheduleConfig {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${cron.period}")
    public void cron() {
        employeeRepository.deleteOldestEntry();
    }
}
