package com.example.employeesoap.api;
//todo перенеси в другой пакет api
// done

import com.example.employeesoap.entity.UserDto;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;

import java.util.Set;

public interface UserMapper {
    //todo когда поменяешь название SignupDto, не забудь поменять название метода
    // done
    User UserDtoToUser(UserDto userDto, Set<Role> roles);
}
