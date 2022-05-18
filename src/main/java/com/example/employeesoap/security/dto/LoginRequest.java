package com.example.employeesoap.security.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    private String login;
    private String password;
}
