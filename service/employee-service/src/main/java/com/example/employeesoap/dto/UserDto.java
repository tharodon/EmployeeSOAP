package com.example.employeesoap.dto;

import com.example.employeesoap.type.AuthStatus;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
    private AuthStatus status;
}
