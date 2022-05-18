package com.example.employeesoap.security.service;

import com.example.employeesoap.security.api.UserMapper;
import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.entity.User;
import com.example.employeesoap.security.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User signupDtoToUser(SignupDto signupRequest, Set<Role> roles){
        return User.builder()
                .username(signupRequest.getUsername())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .roles(roles)
                .build();
    }

}
