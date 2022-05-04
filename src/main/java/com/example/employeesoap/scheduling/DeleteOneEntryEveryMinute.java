package com.example.employeesoap.scheduling;

import com.example.employeesoap.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOneEntryEveryMinute {

    private final EmployeeRepository employeeRepository;

    @Scheduled(cron = "${cronTime}")
    public void cron(){
        employeeRepository.deleteOldestEntry();
    }
}
