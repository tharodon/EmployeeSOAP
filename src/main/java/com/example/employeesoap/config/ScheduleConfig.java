package com.example.employeesoap.config;

import com.example.employeesoap.api.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduleConfig { //todo подумал над названием лучше наверное ScheduleConfig. Поставить Configuration. Имя пакет либо scheduler или config // done

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${cron.period}")
    public void cron() {
        employeeRepository.deleteOldestEntry();
    }
}
