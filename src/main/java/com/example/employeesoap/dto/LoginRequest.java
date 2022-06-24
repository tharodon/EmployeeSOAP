package com.example.employeesoap.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(description = "Логин и пароль пользователя")
public class LoginRequest {
    @Schema(description = "Логин", example = "Ivan123@mail.ru", required = true)
    private String login;

    @Schema(description = "Пароль", example = "YourPasswordExample", required = true)
    private String password;
}
