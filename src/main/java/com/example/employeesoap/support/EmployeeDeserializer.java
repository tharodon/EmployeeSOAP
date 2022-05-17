package com.example.employeesoap.support;
//todo пакет serializer, а внутри Deserializer))), можно в саппорт закинуть и все
// done
import com.example.employeesoap.entity.Employee;
import org.springframework.kafka.support.serializer.JsonDeserializer;

public class EmployeeDeserializer extends JsonDeserializer<Employee> {
}
