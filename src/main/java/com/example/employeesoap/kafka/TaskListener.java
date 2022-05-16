package com.example.employeesoap.kafka;

import com.example.employeesoap.api.EmployeeDao;
import com.example.employeesoap.entity.Employee;
import com.example.employeesoap.exception.JsonParsingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//todo почему эта аннотация. Судя по содержанию это скорее Service
// done
@Slf4j
@Service
@RequiredArgsConstructor
public class TaskListener {

    private final EmployeeDao employeeDao;

    //todo где логи ?
    // done
    @KafkaListener(topics = "${topic.save}")
    public void executeTask(ConsumerRecord<String, String> task) {
        log.debug("TaskListener. Request: key - {}, value - {}", task.key(), task.value());
        try {
            Employee employee = new ObjectMapper().readValue(task.value(), Employee.class);
            employeeDao.save(employee);
        } catch (JsonProcessingException e) {
            //todo ммм, по больше бы инфы + не оч нравиться, что это чистый runtime, а не свое исключение
            // done
            log.debug("Failure parsing: {} Message: {}", task.value(), e.getMessage());
            throw new JsonParsingException(task.value(), e.getMessage());
        }
    }
}
