package com.example.employeesoap.service;

import com.example.employeesoap.api.*;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.entity.UserDto;
import com.example.employeesoap.type.AuthStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;


    @Override
    public UserDto register(UserDto request) {
        UserDto userDto = userValidator.validate(request);
        if (userDto.getStatus() == AuthStatus.OK) {
            //todo лучше вывести в переменную, так как там внутри маппится и не сразу это можно увидеть
            // done
            Set<Role> roles = roleMapper.toRole(userDto.getRoles());
            User user = userMapper.UserDtoToUser(userDto,
                    roles);
            userDao.save(user);
        }
        return userDto;
    }
}
