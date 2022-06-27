package com.example.employeesoap.service.register;

import com.example.employeesoap.api.RegisterService;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.entity.User;
import com.example.employeesoap.repository.UserRepository;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.AuthStatus.ERROR;
import static com.example.employeesoap.type.AuthStatus.OK;
import static com.example.employeesoap.type.RoleName.ROLE_USER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterServiceImplTest extends IntegrationTest {

    private final RegisterService registerService;

    private final UserRepository userRepository;

    private final User user =
            User.builder()
                    .username(USERNAME)
                    .email(EMAIL)
                    .password(PASSWORD)
                    .roles(
                            new HashSet<>() {
                                {
                                    add(new Role(null, ROLE_USER));
                                }
                            })
                    .build();

    private final UserDto userDto =
            UserDto.builder()
                    .username(USERNAME)
                    .email(EMAIL)
                    .password(PASSWORD)
                    .roles(
                            new HashSet<>() {
                                {
                                    add("user");
                                }
                            })
                    .status(OK)
                    .build();

    @Autowired
    public RegisterServiceImplTest(RegisterService registerService, UserRepository userRepository) {
        this.registerService = registerService;
        this.userRepository = userRepository;
    }

    @Test
    void registerSaveShouldSaveUser() {
        registerService.register(userDto);
        assertEquals(user, userRepository.findByUsername(USERNAME).get());
    }

    @Test
    void registerShouldReturnValidUserDto() {
        UserDto register = registerService.register(userDto);
        assertEquals(userDto, register);
    }

    @Test
    void registerStatusWithDuplicateUsernameShouldError() {
        userDto.setUsername(USERNAME_DUPLICATE);
        UserDto register = registerService.register(userDto);
        assertEquals(ERROR, register.getStatus());
    }

    @Test
    void registerStatusWithDuplicateEmailShouldError() {
        userDto.setEmail(EMAIL_DUPLICATE);
        UserDto register = registerService.register(userDto);
        assertEquals(ERROR, register.getStatus());
    }
}
