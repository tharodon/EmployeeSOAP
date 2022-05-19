package com.example.employeesoap.api;

import com.example.employeesoap.entity.UserDto;

public interface RegisterService {
    UserDto register(UserDto request);
}
