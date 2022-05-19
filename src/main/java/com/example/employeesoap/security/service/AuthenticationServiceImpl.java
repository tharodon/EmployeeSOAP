package com.example.employeesoap.security.service;
//todo не понимаю зачем еще один пакет service

import com.example.employeesoap.security.api.AuthenticationService;
import com.example.employeesoap.security.jwt.JwtService;
import com.example.employeesoap.security.dto.JwtResponse;
import com.example.employeesoap.security.dto.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public JwtResponse authenticate(LoginRequest loginRequest) {
        Authentication authentication = getAuthentication(loginRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtService.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = new ArrayList<>();
        if (userDetails.getAuthorities() != null) {
            roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toList()); //todo каждый вызов стрима должен быть с новой строки .collect
        }
        return new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getPassword(),
                userDetails.getEmail(),
                roles);
    }

    private Authentication getAuthentication(LoginRequest loginRequest) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getLogin(),
                loginRequest.getPassword()));
    }
}
