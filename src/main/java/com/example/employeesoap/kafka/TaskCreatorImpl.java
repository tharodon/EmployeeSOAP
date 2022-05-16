package com.example.employeesoap.kafka;

import com.example.employeesoap.api.TaskCreator;
import com.example.employeesoap.entity.Employee;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskCreatorImpl implements TaskCreator {

    @Value("${topic.save}")
    private String topicSaveName;
    private final KafkaTemplate<String, Employee> kafkaTemplate;

    //todo где логи ?
    // done
    @Override
    public void createTask(@NonNull Employee employee) {
        log.debug("request task: {}", employee);
        ListenableFuture<SendResult<String, Employee>> send = kafkaTemplate.send(topicSaveName, employee.getUid(), employee);
        send.addCallback(new ListenableFutureCallback<SendResult<String, Employee>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.debug("Failure send: {}", ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Employee> result) {
                log.debug("Success send: {}", result);
            }
        });
        kafkaTemplate.flush();
    }
}