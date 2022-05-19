package com.example.employeesoap.api;
//todo перенеси в другой пакет api
// done

import com.example.employeesoap.entity.UserDto;

public interface UserValidator {
    UserDto validate(UserDto request);
}
