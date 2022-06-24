package com.example.employeesoap.controller;

import static com.example.employeesoap.support.testdata.Constants.*;
import static com.example.employeesoap.type.AuthStatus.ERROR;
import static com.example.employeesoap.type.AuthStatus.OK;
import static org.junit.jupiter.api.Assertions.*;

import com.example.employeesoap.dto.JwtResponse;
import com.example.employeesoap.dto.LoginRequest;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.repository.UserRepository;
import com.example.employeesoap.support.IntegrationTest;
import java.util.HashSet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

class AuthControllerTest extends IntegrationTest {

    private final AuthController authController;

    private final UserRepository userRepository;

    @Autowired
    public AuthControllerTest(AuthController authController, UserRepository userRepository) {
        this.authController = authController;
        this.userRepository = userRepository;
    }

    @Test
    void registerValidUserTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(null)
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(OK, responseEntity.getBody().getStatus());
        assertTrue(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithEmptyUsernameTest() {
        UserDto userDto =
                UserDto.builder().username("").roles(null).email(EMAIL).password(PASSWORD).build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserWithEmptyEmailTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(null)
                        .email("")
                        .password(PASSWORD)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        assertFalse(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithEmptyPasswordTest() {
        UserDto userDto =
                UserDto.builder().username(USERNAME).roles(null).email(EMAIL).password("").build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        assertFalse(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithNullUsernameTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(null)
                        .roles(null)
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        assertFalse(userRepository.existsByEmail(EMAIL));
    }

    @Test
    void registerUserWithNullEmailTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(null)
                        .email(null)
                        .password(PASSWORD)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        assertFalse(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserWithNullPasswordTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(null)
                        .email(EMAIL)
                        .password(null)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        assertFalse(userRepository.existsByUsername(USERNAME));
    }

    @Test
    void registerUserDuplicateUsernameTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME_DUPLICATE)
                        .roles(null)
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        long count =
                userRepository.findAll().stream()
                        .filter(user -> user.getUsername().equals(USERNAME_DUPLICATE))
                        .count();
        assertSame(count, 1L);
    }

    @Test
    void registerUserDuplicateEmailTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(null)
                        .email(EMAIL_DUPLICATE)
                        .password(PASSWORD)
                        .build();
        ResponseEntity<UserDto> responseEntity =
                (ResponseEntity<UserDto>) authController.registerUser(userDto);
        assertSame(ERROR, responseEntity.getBody().getStatus());
        long count =
                userRepository.findAll().stream()
                        .filter(user -> user.getEmail().equals(EMAIL_DUPLICATE))
                        .count();
        assertSame(count, 1L);
    }

    @Test
    void authValidUserWithoutRolesTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(null)
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
        authController.registerUser(userDto);
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);
        ResponseEntity<JwtResponse> responseEntity =
                (ResponseEntity<JwtResponse>) authController.authUser(loginRequest);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(USERNAME, responseEntity.getBody().getUsername());
        assertSame(1, responseEntity.getBody().getRoles().size());
        assertEquals("ROLE_USER", responseEntity.getBody().getRoles().get(0));
        assertNotNull(responseEntity.getBody().getToken());
        assertFalse(responseEntity.getBody().getToken().isEmpty());
    }

    @Test
    void authValidUserWithRoleAdminTest() {
        UserDto userDto =
                UserDto.builder()
                        .username(USERNAME)
                        .roles(
                                new HashSet<String>() {
                                    {
                                        add("admin");
                                    }
                                })
                        .email(EMAIL)
                        .password(PASSWORD)
                        .build();
        authController.registerUser(userDto);
        LoginRequest loginRequest = new LoginRequest(USERNAME, PASSWORD);
        ResponseEntity<JwtResponse> responseEntity =
                (ResponseEntity<JwtResponse>) authController.authUser(loginRequest);
        assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
        assertEquals(USERNAME, responseEntity.getBody().getUsername());
        assertSame(1, responseEntity.getBody().getRoles().size());
        assertEquals("ROLE_ADMIN", responseEntity.getBody().getRoles().get(0));
        assertNotNull(responseEntity.getBody().getToken());
        assertFalse(responseEntity.getBody().getToken().isEmpty());
    }
}
