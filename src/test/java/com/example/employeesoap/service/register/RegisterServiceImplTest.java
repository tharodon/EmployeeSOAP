package com.example.employeesoap.service.register;

import com.example.employeesoap.IntegrationTest;
import com.example.employeesoap.api.RegisterService;
import com.example.employeesoap.api.UserMapper;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.repository.UserRepository;
import com.example.employeesoap.service.mapper.UserMapperImpl;
import com.example.employeesoap.type.AuthStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;

import static com.example.employeesoap.type.AuthStatus.*;
import static org.junit.jupiter.api.Assertions.*;
import static com.example.employeesoap.type.RoleName.*;
import static com.example.employeesoap.Constants.*;

class RegisterServiceImplTest extends IntegrationTest {

    private final RegisterService registerService;

    private final UserRepository userRepository;

    private final UserMapper userMapper = new UserMapperImpl(new BCryptPasswordEncoder());

    private final User user = User.builder()
            .username(USERNAME)
            .email(EMAIL)
            .password(PASSWORD)
            .roles(new HashSet<Role>(){{add(new Role(null, ROLE_USER));}})
            .build();

    private final UserDto userDto = UserDto.builder()
            .username(USERNAME)
            .email(EMAIL)
            .password(PASSWORD)
            .roles(new HashSet<String>(){{add("user");}})
            .status(OK)
            .build();

    @Autowired
    public RegisterServiceImplTest(RegisterService registerService, UserRepository userRepository) {
        this.registerService = registerService;
        this.userRepository = userRepository;
    }

    @Test
    void registerSaveTest() {
        registerService.register(userDto);
        assertEquals(user, userRepository.findByUsername(USERNAME).get());
    }

    @Test
    void registerReturnTest() {
        UserDto register = registerService.register(userDto);
        assertEquals(userDto, register);
    }

    @Test
    void registerStatusWithDuplicateUsernameTest() {
        userDto.setUsername(USERNAME_DUPLICATE);
        UserDto register = registerService.register(userDto);
        assertEquals(ERROR, register.getStatus());
    }

    @Test
    void registerStatusWithDuplicateEmailTest() {
        userDto.setEmail(EMAIL_DUPLICATE);
        UserDto register = registerService.register(userDto);
        assertEquals(ERROR, register.getStatus());
    }
}