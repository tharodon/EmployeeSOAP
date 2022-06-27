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
                            new HashSet<>() {
                                {
                                    add(USER);
                                }
                            })
                    .status(OK)
                    .build();

    @Autowired
    public UserValidatorImplTest(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Test
    void validateCorrectUserShouldSuccess() {
        assertEquals(userDtoTemplate, userValidator.validate(userDtoTemplate));
    }

    @Test
    void validateEmptyUsernameShouldGetError() {
        userDtoTemplate.setUsername("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullUsernameShouldGetError() {
        userDtoTemplate.setUsername(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateExistUsernameShouldGetError() {
        userDtoTemplate.setUsername(USERNAME_DUPLICATE);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateEmptyEmailShouldGetError() {
        userDtoTemplate.setEmail("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullEmailShouldGetError() {
        userDtoTemplate.setEmail(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateEmptyPasswordShouldGetError() {
        userDtoTemplate.setPassword("");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateNullPasswordShouldGetError() {
        userDtoTemplate.setPassword(null);
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }

    @Test
    void validateIncorrectRoleShouldGetError() {
        userDtoTemplate.getRoles().add("incorrect_role");
        assertEquals(ERROR, userValidator.validate(userDtoTemplate).getStatus());
    }
}
