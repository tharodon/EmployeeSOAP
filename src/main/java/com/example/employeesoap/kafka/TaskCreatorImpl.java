package com.example.employeesoap.kafka;

import com.example.employeesoap.api.TaskCreator;
import com.example.employeesoap.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskCreatorImpl implements TaskCreator {

    @Value("${topic.save}")
    private String topicSaveName;
    private final KafkaTemplate<String, Employee> kafkaTemplate;

    @Override
    public void createTask(Employee employee) { //todo где логи ?
        kafkaTemplate.send(topicSaveName, employee.getId(), employee);
        kafkaTemplate.flush();
    }
}
