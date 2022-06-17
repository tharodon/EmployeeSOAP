package com.example.employeesoap.service.mapper;

import com.example.employeesoap.api.UserMapper;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder passwordEncoder;

    @Override
    public User UserDtoToUser(UserDto userDto, Set<Role> roles) {
        return User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(roles)
                .build();
    }

}
