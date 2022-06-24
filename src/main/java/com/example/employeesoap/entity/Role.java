package com.example.employeesoap.entity;

import com.example.employeesoap.type.RoleName;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleName name;
}
