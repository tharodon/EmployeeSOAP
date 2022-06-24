/* (C)2022 */
package com.example.employeesoap.support;

import com.example.employeesoap.entity.Employee;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {}
