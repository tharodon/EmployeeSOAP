package com.example.employeesoap.security.api;

import com.example.employeesoap.security.dto.SignupDto;

public interface UserValidator {
    SignupDto validate(SignupDto request);
}
