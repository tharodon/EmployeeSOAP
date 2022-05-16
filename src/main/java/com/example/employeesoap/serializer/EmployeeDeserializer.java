package com.example.employeesoap.serializer;

import com.example.employeesoap.entity.Employee;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {
}
