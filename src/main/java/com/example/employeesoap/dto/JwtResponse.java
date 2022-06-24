package com.example.employeesoap.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Сущность информации об авторизации пользователя")
public class JwtResponse {
    @Schema(description = "Тип авторизации")
    private final String type = "Bearer";

    @Schema(description = "Токен")
    private String token;

    @Schema(description = "id пользователя")
    private Long userId;

    @Schema(description = "Имя пользователя")
    private String username;

    @Schema(description = "Email пользователя")
    private String email;

    @Schema(description = "Права доступа")
    private List<String> roles;
}
