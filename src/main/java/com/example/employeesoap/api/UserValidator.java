/* (C)2022 */
package com.example.employeesoap.api;

import com.example.employeesoap.dto.UserDto;

public interface UserValidator {
    UserDto validate(UserDto request);
}
