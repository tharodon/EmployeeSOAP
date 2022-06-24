package com.example.employeesoap.api;


import com.example.employeesoap.dto.UserDto;

public interface RegisterService {
    UserDto register(UserDto request);
}
