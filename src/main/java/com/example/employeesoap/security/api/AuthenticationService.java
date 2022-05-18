package com.example.employeesoap.security.api;

import com.example.employeesoap.security.dto.JwtResponse;
import com.example.employeesoap.security.dto.LoginRequest;

public interface AuthenticationService {
    JwtResponse authenticate(LoginRequest loginRequest);
}
