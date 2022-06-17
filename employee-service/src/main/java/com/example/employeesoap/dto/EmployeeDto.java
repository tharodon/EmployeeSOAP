package com.example.employeesoap.dto;

import com.example.employeesoap.type.Position;
import com.example.employeesoap.type.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Сущность работника")
public class EmployeeDto {
    private String uid;
    @Schema(description = "Имя", required = true)
    private String name;
    @Schema(description = "Фамилия", required = true)
    private String surname;
    @Schema(description = "Позиция", required = true, oneOf = Position.class)
    private String position;
    @Schema(description = "Уровень")
    private String grade;
    @Schema(description = "Описание")
    private String description;
    @Schema(description = "Возраст", required = true, minimum = "18")
    private String age;
    @Schema(description = "Зарплата", required = true)
    private String salary;
    @Schema(description = "uid задач работника")
    private String tasksUID;
    @Schema(hidden = true)
    private Status status;
}
