package com.example.employeesoap.kafka;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exceptions.JsonParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//todo почему эта аннотация. Судя по содержанию это скорее Service
// done
@Service
@EnableKafka //todo думаю эта аннотация должна быть в другом месте
@RequiredArgsConstructor
public class TaskListener {

    private final EmployeeDao employeeDao;

    @KafkaListener(topics = "${topic.save}")
    public void executeTask(ConsumerRecord<String, String> task) { //todo где логи ?
        try {
            Employee employee = new ObjectMapper().readValue(task.value(), Employee.class);
            employeeDao.save(employee);
        } catch (JsonProcessingException e) {
            //todo ммм, по больше бы инфы + не оч нравиться, что это чистый runtime, а не свое исключение
            // done
            throw new JsonParsingException(task.value(), e.getMessage());
        }
    }
}
