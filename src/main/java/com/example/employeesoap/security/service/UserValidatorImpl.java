package com.example.employeesoap.security.service;

import com.example.employeesoap.security.api.UserValidator;
import com.example.employeesoap.security.dto.SignupDto;
import com.example.employeesoap.security.repository.UserRepository;
import com.example.employeesoap.security.type.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.employeesoap.security.type.AuthStatus.*;

@Service
@RequiredArgsConstructor
public class UserValidatorImpl implements UserValidator {

    private static final String AUTH_INVALID_ROLE = "auth.invalid.role";
    private static final String USER_ROLE = "user";
    private final ResourceBundle resourceBundle =
            ResourceBundle.getBundle(FILENAME, new Locale("US", "US"));
    private static final String AUTH_REGISTER_USERNAME = "auth.register.username";
    private static final String AUTH_REGISTER_EMAIL = "auth.register.email";
    private static final String AUTH_REGISTER_PASSWORD = "auth.register.password";
    private static final String FILENAME = "messages_US";
    private final UserRepository userRepository;

    @Override
    public SignupDto validate(SignupDto request) {
        SignupDto signupDto = new SignupDto();
        signupDto.setStatus(OK);
        if (userRepository.existsByUsername(request.getUsername())) {
            signupDto.setUsername(resourceBundle.getString(AUTH_REGISTER_USERNAME));
            signupDto.setStatus(ERROR);
        }else {
            signupDto.setUsername(request.getUsername());
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            signupDto.setEmail(resourceBundle.getString(AUTH_REGISTER_EMAIL));
            signupDto.setStatus(ERROR);
        }else {
            signupDto.setEmail(request.getEmail());
        }
        if (request.getPassword().isEmpty()) {
            signupDto.setPassword(resourceBundle.getString(AUTH_REGISTER_PASSWORD));
            signupDto.setStatus(ERROR);
        }else {
            signupDto.setPassword(request.getPassword());
        }
        if (Objects.isNull(request.getRoles()) || request.getRoles().isEmpty()){
            signupDto.setRoles(new HashSet<String>(){{
                add(USER_ROLE);
            }});
        }else {
            signupDto.setRoles(request.getRoles().stream()
                    .map(role -> checkRole(role, signupDto))
                    .collect(Collectors.toSet()));
        }
        return signupDto;
    }


    private String checkRole(String role, SignupDto signupDto) {
        if (Objects.isNull(ERole.getERole(role))) {
            signupDto.setStatus(ERROR);
            return MessageFormat.format(resourceBundle.getString(AUTH_INVALID_ROLE), role);
        }
        return role;
    }
}
