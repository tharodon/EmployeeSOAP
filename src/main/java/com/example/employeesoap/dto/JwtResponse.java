package com.example.employeesoap.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private Long userId;
    private String username;
    private String email;
    private List<String> roles;
}
