package com.example.employeesoap.security.service;

import com.example.employeesoap.security.api.*;
import com.example.employeesoap.security.entity.User;
import com.example.employeesoap.security.dto.SignupDto;
import com.example.employeesoap.security.type.AuthStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final UserDao userDao;
    private final UserValidator userValidator;
    private final UserMapper userMapper;
    private final RoleMapper roleMapper;


    @Override
    public SignupDto register(SignupDto request) {
        SignupDto signupDto = userValidator.validate(request);
        if (signupDto.getStatus() == AuthStatus.OK) {
            User user = userMapper.signupDtoToUser(signupDto,
                    roleMapper.stringToRole(signupDto.getRoles()));
            userDao.save(user);
        }
        return signupDto;
    }
}
