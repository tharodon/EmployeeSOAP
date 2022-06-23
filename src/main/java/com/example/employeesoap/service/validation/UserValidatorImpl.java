package com.example.employeesoap.service.validation;

import com.example.employeesoap.api.UserValidator;
import com.example.employeesoap.dto.UserDto;
import com.example.employeesoap.repository.UserRepository;
import com.example.employeesoap.type.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.employeesoap.type.AuthStatus.*;

@Service
@RequiredArgsConstructor
public class UserValidatorImpl implements UserValidator {

    private static final String AUTH_INVALID_ROLE = "auth.invalid.role";
    private static final String USER_ROLE = "user";
    private final ResourceBundle resourceBundle =
            ResourceBundle.getBundle(FILENAME, new Locale("US", "US"));
    private static final String AUTH_REGISTER_USERNAME = "auth.register.username";
    private static final String AUTH_REGISTER_USERNAME_EMPTY = "auth.register.username.empty";
    private static final String AUTH_REGISTER_EMAIL = "auth.register.email";
    private static final String AUTH_REGISTER_EMAIL_EMPTY = "auth.register.email.empty";
    private static final String AUTH_REGISTER_PASSWORD = "auth.register.password";
    private static final String FILENAME = "messages_US";
    private final UserRepository userRepository;

    @Override
    public UserDto validate(UserDto request) {
        UserDto userDto = new UserDto();
        userDto.setStatus(OK);
        usernameValidate(request, userDto);

        emailValidate(request, userDto);

        passwordValidate(request, userDto);

        rolesValidate(request, userDto);
        return userDto;
    }

    private void rolesValidate(UserDto request, UserDto userDto) {
        if (Objects.isNull(request.getRoles()) || request.getRoles().isEmpty()) {
            userDto.setRoles(new HashSet<String>() {{
                add(USER_ROLE);
            }});
        } else {
            userDto.setRoles(request.getRoles().stream()
                    .map(role -> checkRole(role, userDto))
                    .collect(Collectors.toSet()));
        }
    }

    private void passwordValidate(UserDto request, UserDto userDto) {
        if (request.getPassword() == null || request.getPassword().isEmpty()) {
            userDto.setPassword(resourceBundle.getString(AUTH_REGISTER_PASSWORD));
            userDto.setStatus(ERROR);
        } else {
            userDto.setPassword(request.getPassword());
        }
    }

    private void emailValidate(UserDto request, UserDto userDto) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            userDto.setEmail(resourceBundle.getString(AUTH_REGISTER_EMAIL_EMPTY));
            userDto.setStatus(ERROR);
        } else if (userRepository.existsByEmail(request.getEmail())) {
            userDto.setEmail(resourceBundle.getString(AUTH_REGISTER_EMAIL));
            userDto.setStatus(ERROR);
        } else {
            userDto.setEmail(request.getEmail());
        }
    }

    private void usernameValidate(UserDto request, UserDto userDto) {
        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            userDto.setUsername(resourceBundle.getString(AUTH_REGISTER_USERNAME_EMPTY));
            userDto.setStatus(ERROR);
        } else if (userRepository.existsByUsername(request.getUsername())) {
            userDto.setUsername(resourceBundle.getString(AUTH_REGISTER_USERNAME));
            userDto.setStatus(ERROR);
        } else {
            userDto.setUsername(request.getUsername());
        }
    }


    private String checkRole(String role, UserDto userDto) {
        if (Objects.isNull(RoleName.getRoleName(role))) {
            userDto.setStatus(ERROR);
            return MessageFormat.format(resourceBundle.getString(AUTH_INVALID_ROLE), role);
        }
        return role;
    }
}
