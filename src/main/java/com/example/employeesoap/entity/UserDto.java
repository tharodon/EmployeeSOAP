package com.example.employeesoap.entity;

import com.example.employeesoap.type.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
    private AuthStatus status;
}
