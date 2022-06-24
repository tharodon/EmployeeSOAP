/* (C)2022 */
package com.example.employeesoap.controller;

import com.example.employeesoap.api.AuthenticationService;
import com.example.employeesoap.api.RegisterService;
import com.example.employeesoap.dto.LoginRequest;
import com.example.employeesoap.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@Tag(name = "Контроллер аутентификации")
public class AuthController {

    private final RegisterService registerService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "Аутентификация в системе")
    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        log.info("Signin: {}", loginRequest);
        return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto request) {
        log.info("Register: {}", request);
        return ResponseEntity.ok(registerService.register(request));
    }
}
