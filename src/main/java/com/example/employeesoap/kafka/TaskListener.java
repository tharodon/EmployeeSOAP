package com.example.employeesoap.kafka;


import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskListener {

    private final EmployeeDao employeeDao;

    @KafkaListener(topics = "${topic.save}")
    public void executeTask(ConsumerRecord<String, Employee> task) {
        log.info("TaskListener. Request: key - {}, value - {}", task.key(), task.value());
        employeeDao.save(task.value());
    }
}
