package com.example.employeesoap.security.dto;

import com.example.employeesoap.security.type.AuthStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupDto { //todo не очень название глаголDto
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
    private AuthStatus status;
}
