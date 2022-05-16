package com.example.employeesoap.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface TaskListener { //todo интерфейс в пакет api
    void executeTask(ConsumerRecord<String, String> task); //todo не используется
}
