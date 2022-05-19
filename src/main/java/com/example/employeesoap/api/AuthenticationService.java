package com.example.employeesoap.api;

import com.example.employeesoap.entity.JwtResponse;
import com.example.employeesoap.entity.LoginRequest;

public interface AuthenticationService {
    JwtResponse authenticate(LoginRequest loginRequest);
}
