package com.example.employeesoap.kafka;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class TaskListenerImpl implements TaskListener {

    private final EmployeeDao employeeDao;

    @Override
    @KafkaListener(topics = "${topic.save}")
    public void executeTask(ConsumerRecord<String, String> task) {
        try {
            Employee employee = new ObjectMapper().readValue(task.value(), Employee.class);
            employeeDao.save(employee);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
