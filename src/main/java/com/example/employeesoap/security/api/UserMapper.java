package com.example.employeesoap.security.api;

import com.example.employeesoap.security.dto.SignupDto;
import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.entity.User;

import java.util.Set;

public interface UserMapper {
    User signupDtoToUser(SignupDto signupRequest, Set<Role> roles);
}
