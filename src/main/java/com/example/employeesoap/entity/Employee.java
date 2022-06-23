package com.example.employeesoap.entity;

import com.example.employeesoap.type.Position;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Builder(toBuilder = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"tasks", "id"})
@ToString(exclude = {"tasks"})
@Table(name = "employee")
@Schema(description = "Сущность работника")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;
    @Column
    @Schema(hidden = true)
    private String uid;
    @Column
    @Schema(description = "Имя", required = true)
    private String name;
    @Column
    @Schema(description = "Фамилия", required = true)
    private String surname;
    @Column
    @Schema(description = "Позиция", required = true, oneOf = Position.class)
    private String position;
    @Column
    @Schema(description = "Уровень")
    private String grade;
    @Column
    @Schema(description = "Описание")
    private String description;
    @Column
    @Schema(description = "Возраст", required = true, minimum = "18")
    private Long age;
    @Column
    @Schema(description = "Зарплата", required = true)
    private Long salary;

    @Schema(description = "Задачи работника")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "distribute",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks = new ArrayList<>();
}
