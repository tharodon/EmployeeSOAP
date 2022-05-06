package com.example.employeesoap.dto;

import lombok.*;

import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeDto {

    private Long id;
    private String name;
    private String surname;
    private String position;
    private String grade;
    private String description;
    private Long age;
    private Long salary;
    private Long[] tasksUID;
}
