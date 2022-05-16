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

@Configuration //todo почему эта аннотация. Судя по содержанию это скорее Service
@EnableKafka //todo думаю эта аннотация должна быть в другом месте
@RequiredArgsConstructor
public class TaskListenerImpl implements TaskListener {

    private final EmployeeDao employeeDao;

    @Override
    @KafkaListener(topics = "${topic.save}")
    public void executeTask(ConsumerRecord<String, String> task) { //todo где логи ?
        try {
            Employee employee = new ObjectMapper().readValue(task.value(), Employee.class);
            employeeDao.save(employee);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); //todo ммм, по больше бы инфы + не оч нравиться, что это чистый runtime, а не свое исключение
        }
    }
}
