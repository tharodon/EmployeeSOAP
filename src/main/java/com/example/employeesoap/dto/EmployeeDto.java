package com.example.employeesoap.dto;

import com.example.employeesoap.type.Status;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeDto {

    private String uid;
    private String name;
    private String surname;
    private String position;
    private String grade;
    private String description;
    private String age;
    private String salary;
    private String tasksUID;
    private Status status;
}
