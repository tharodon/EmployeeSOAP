package com.example.employeesoap.security.api;
//todo перенеси в другой пакет api
import com.example.employeesoap.security.dto.SignupDto;
import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.entity.User;

import java.util.Set;

public interface UserMapper {
    User signupDtoToUser(SignupDto signupRequest, Set<Role> roles); //todo когда поменяешь название SignupDto, не забудь поменять название метода
}
