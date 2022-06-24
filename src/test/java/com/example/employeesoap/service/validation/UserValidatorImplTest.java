package com.example.employeesoap.service.validation;

import com.example.employeesoap.api.UserValidator;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.AuthStatus.ERROR;
import static com.example.employeesoap.type.AuthStatus.OK;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserValidatorImplTest extends IntegrationTest {

    private final UserValidator userValidator;

    UserDto userDtoTemplate =
            UserDto.builder()
                    .username(USERNAME)
                    .email(EMAIL)
                    .password(PASSWORD)
                    .roles(
                            new HashSet<String>() {
                                {
                                    add("user");
                                }
                            })
                    .status(OK)
                    .build();

    @Autowired
    public UserValidatorImplTest(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Test
    void validateCorrectUserTest() {
        assertEquals(userDtoTemplate, userValidator.validate(userDtoTemplate));
    }

    @Test
    void validateEmptyUsernameShouldErrorTest() {
        userDtoTemplate.setUsername("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullUsernameShouldErrorTest() {
        userDtoTemplate.setUsername(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateExistUsernameShouldErrorTest() {
        userDtoTemplate.setUsername(USERNAME_DUPLICATE);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateEmptyEmailShouldErrorTest() {
        userDtoTemplate.setEmail("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullEmailShouldErrorTest() {
        userDtoTemplate.setEmail(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateEmptyPasswordShouldErrorTest() {
        userDtoTemplate.setPassword("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullPasswordShouldErrorTest() {
        userDtoTemplate.setPassword(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateIncorrectRoleShouldErrorTest() {
        userDtoTemplate.getRoles().add("incorrect_role");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }
}
