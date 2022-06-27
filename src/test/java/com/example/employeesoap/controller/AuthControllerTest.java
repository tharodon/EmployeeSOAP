package com.example.employeesoap.controller;

import com.example.employeesoap.dto.JwtResponse;
import com.example.employeesoap.dto.LoginRequest;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.repository.UserRepository;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Objects;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.AuthStatus.ERROR;
import static com.example.employeesoap.type.AuthStatus.OK;
import static org.junit.jupiter.api.Assertions.*;

class AuthControllerTest extends IntegrationTest {

    private final AuthController authController;

    private final UserRepository userRepository;

    @Autowired
    public AuthControllerTest(AuthController authController, UserRepository userRepository) {
        this.authController = authController;
        this.userRepository = userRepository;
    }

    @Test
    void registerValidUserShouldGetOkStatus() {
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(getValidUserDto());
        assertSame(OK, Objects.requireNonNull(responseEntity.getBody()).getStatus());
        assertTrue(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerValidUserShouldExist() {
        authController.registerUser(getValidUserDto());
        assertTrue(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithEmptyUsernameShouldGetError() {
        UserDto userDto = getValidUserDto();
        userDto.setUsername("");
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserWithEmptyUsernameShouldNotExists() {
        UserDto userDto = getValidUserDto();
        userDto.setUsername("");
        authController.registerUser(userDto);
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserWithEmptyEmailShouldGetError() {
        UserDto userDto = getValidUserDto();
        userDto.setEmail("");
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void registerUserWithEmptyEmailShouldNotExists() {
        UserDto userDto = getValidUserDto();
        userDto.setEmail("");
        authController.registerUser(userDto);
        assertFalse(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithEmptyPasswordShouldGetError() {
        UserDto userDto = getValidUserDto();
        userDto.setPassword("");
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void registerUserWithEmptyPasswordShouldNotExists() {
        UserDto userDto = getValidUserDto();
        userDto.setPassword("");
        authController.registerUser(userDto);
        assertFalse(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithNullUsernameShouldGetError() {
        UserDto userDto = getValidUserDto();
        userDto.setUsername(null);
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void registerUserWithNullUsernameShouldNotExists() {
        UserDto userDto = getValidUserDto();
        userDto.setUsername(null);
        authController.registerUser(userDto);
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserWithNullEmailShouldGetError() {
        UserDto userDto = getValidUserDto();
        userDto.setEmail(null);
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void registerUserWithNullEmailShouldNotExists() {
        UserDto userDto = getValidUserDto();
        userDto.setEmail(null);
        authController.registerUser(userDto);
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserWithNullPasswordShouldGetError() {
        UserDto userDto = getValidUserDto();
        userDto.setPassword(null);
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void registerUserWithNullPasswordShouldNotExists() {
        UserDto userDto = getValidUserDto();
        userDto.setPassword(null);
        authController.registerUser(userDto);
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserDuplicateUsernameShouldGetError() {
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(getUserDtoWithUsernameDuplicate());
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void registerUserDuplicateEmailShouldGetError() {
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(getUserDtoWithEmailDuplicate());
        assertSame(ERROR, Objects.requireNonNull(responseEntity.getBody()).getStatus());
    }

    @Test
    void authValidUserWithoutRolesShouldGetOkStatus() {
        authController.registerUser(getValidUserDto());
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);
        ResponseEntity<JwtResponse> responseEntity =
                (ResponseEntity<JwtResponse>) authController.authUser(loginRequest);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    void authValidUserWithoutRolesShouldTokenNotEmpty() {
        authController.registerUser(getValidUserDto());
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);
        ResponseEntity<JwtResponse> responseEntity =
                (ResponseEntity<JwtResponse>) authController.authUser(loginRequest);
        assertFalse(responseEntity.getBody().getToken().isEmpty());
    }

    @Test
    void authValidUserWithRoleAdminOkStatus() {
        UserDto userDto = getValidUserDto();
        userDto.setRoles(
                new HashSet<>() {
                    {
                        add("admin");
                    }
                });
        authController.registerUser(userDto);
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);
        ResponseEntity<JwtResponse> responseEntity =
                (ResponseEntity<JwtResponse>) authController.authUser(loginRequest);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertFalse(responseEntity.getBody().getToken().isEmpty());
    }
}
