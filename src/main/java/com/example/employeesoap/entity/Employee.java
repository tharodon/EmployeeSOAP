package com.example.employeesoap.entity;

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
@EqualsAndHashCode(exclude = {"tasks"})
@ToString(exclude = {"tasks"})
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String uid;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String position;
    @Column
    private String grade;
    @Column
    private String description;
    @Column
    private Long age;
    @Column
    private Long salary;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "distribute",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private List<Task> tasks = new ArrayList<>();
}
