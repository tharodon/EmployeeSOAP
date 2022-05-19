package com.example.employeesoap.security.api;
//todo перенеси в другой пакет api
import com.example.employeesoap.security.dto.SignupDto;

public interface UserValidator {
    SignupDto validate(SignupDto request);
}
