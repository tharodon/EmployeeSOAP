package com.example.employeesoap.dto;


import com.example.employeesoap.type.AuthStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Set;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Builder
@Schema(description = "Сущность пользователя")
public class UserDto {
    @Schema(description = "Имя пользователя", required = true)
    private String username;

    @Schema(description = "Email", required = true)
    private String email;

    @Schema(description = "Права доступа")
    private Set<String> roles;

    @Schema(description = "Пароль пользователя", required = true)
    private String password;

    @Schema(hidden = true)
    private AuthStatus status;
}
