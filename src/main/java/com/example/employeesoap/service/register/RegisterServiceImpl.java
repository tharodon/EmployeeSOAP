/* (C)2022 */
package com.example.employeesoap.service.register;

import com.example.employeesoap.api.*;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.type.AuthStatus;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
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
            Set<Role> roles = roleMapper.toRole(userDto.getRoles());
            User user = userMapper.userDtoToUser(userDto, roles);
            userDao.save(user);
        }
        log.info("Register response: {}", userDto);
        return userDto;
    }
}
