package com.example.employeesoap.security.controller;

import com.example.employeesoap.security.api.AuthenticationService;
import com.example.employeesoap.security.api.RegisterService;
import com.example.employeesoap.security.dto.LoginRequest;
import com.example.employeesoap.security.dto.SignupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.authenticate(loginRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto request) {
        return ResponseEntity.ok(registerService.register(request));
    }
}
