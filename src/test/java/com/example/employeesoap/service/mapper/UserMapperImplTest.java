package com.example.employeesoap.service.mapper;

import com.example.employeesoap.api.UserMapper;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.example.employeesoap.support.testdata.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserMapperImplTest {
    private final UserMapper userMapper = new UserMapperImpl(new BCryptPasswordEncoder());

    private final User user =
            User.builder().username(USERNAME).email(EMAIL).password(PASSWORD).build();

    private final UserDto userDto =
            UserDto.builder().username(USERNAME).email(EMAIL).password(PASSWORD).build();

    @Test
    void userDtoToUser() {
        User res = userMapper.userDtoToUser(userDto, null);
        assertEquals(user, res);
        assertTrue(new BCryptPasswordEncoder().matches(PASSWORD, res.getPassword()));
    }
}
