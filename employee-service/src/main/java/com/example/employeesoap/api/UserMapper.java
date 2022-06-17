package com.example.employeesoap.api;

import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;

import java.util.Set;

public interface UserMapper {
    User UserDtoToUser(UserDto userDto, Set<Role> roles);
}
