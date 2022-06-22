package com.example.employeesoap.service.validation;

import com.example.employeesoap.IntegrationTest;
import com.example.employeesoap.api.UserValidator;
import com.example.employeesoap.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

import static com.example.employeesoap.Constants.*;
import static com.example.employeesoap.type.AuthStatus.ERROR;
import static com.example.employeesoap.type.AuthStatus.OK;
import static org.junit.jupiter.api.Assertions.*;

class UserValidatorImplTest extends IntegrationTest {

    private final UserValidator userValidator;

    UserDto userDtoTemplate = UserDto.builder()
            .username(USERNAME)
            .email(EMAIL)
            .password(PASSWORD)
            .roles(new HashSet<String>() {{
                add("user");
            }})
            .status(OK)
            .build();

    @Autowired
    public UserValidatorImplTest(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Test
    void validateCorrectTest() {
        assertEquals(userDtoTemplate, userValidator.validate(userDtoTemplate));
    }

    @Test
    void validateEmptyUsernameTest() {
        userDtoTemplate.setUsername("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullUsernameTest() {
        userDtoTemplate.setUsername(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateExistUsernameTest() {
        userDtoTemplate.setUsername(USERNAME_DUPLICATE);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateEmptyEmailTest() {
        userDtoTemplate.setEmail("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullEmailTest() {
        userDtoTemplate.setEmail(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateEmptyPasswordTest() {
        userDtoTemplate.setPassword("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullPasswordTest() {
        userDtoTemplate.setPassword(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateIncorrectRoleTest() {
        userDtoTemplate.getRoles().add("incorrect_role");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }
}