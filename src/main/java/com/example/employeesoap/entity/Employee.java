package com.example.employeesoap.entity;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    //todo используй lombok)))
    public String getName() {
        return name;
    }

    public Employee setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Employee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Employee setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getGrade() {
        return grade;
    }

    public Employee setGrade(String grade) {
        this.grade = grade;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Employee setDescription(String description) {
        this.description = description;
        return this;
    }

    public Long getAge() {
        return age;
    }

    public Employee setAge(Long age) {
        this.age = age;
        return this;
    }

    public Long getSalary() {
        return salary;
    }

    public Employee setSalary(Long salary) {
        this.salary = salary;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Employee setId(Long id) {
        this.id = id;
        return this;
    }
}
