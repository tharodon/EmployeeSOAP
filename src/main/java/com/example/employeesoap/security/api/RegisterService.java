package com.example.employeesoap.security.api;

import com.example.employeesoap.security.dto.SignupDto;

public interface RegisterService {
    SignupDto register(SignupDto request);
}
