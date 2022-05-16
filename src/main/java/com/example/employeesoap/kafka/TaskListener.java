package com.example.employeesoap.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface TaskListener {
    void executeTask(ConsumerRecord<String, String> task);
}
