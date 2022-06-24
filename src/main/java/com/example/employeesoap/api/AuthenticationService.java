package com.example.employeesoap.api;


import com.example.employeesoap.dto.JwtResponse;
import com.example.employeesoap.dto.LoginRequest;

public interface AuthenticationService {
    JwtResponse authenticate(LoginRequest loginRequest);
}
