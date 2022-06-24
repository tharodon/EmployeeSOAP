/* (C)2022 */
package com.example.employeesoap.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "task")
@Schema(description = "Сущность задачи пользователя")
public class Task {
    @Id
    @Column(name = "uid", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long uid;

    @Column
    @Schema(description = "Описание задачи", required = true)
    private String description;
}
