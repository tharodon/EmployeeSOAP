package com.example.employeesoap.api;

import com.example.employeesoap.entity.UserDto;

public interface UserValidator {
    UserDto validate(UserDto request);
}
