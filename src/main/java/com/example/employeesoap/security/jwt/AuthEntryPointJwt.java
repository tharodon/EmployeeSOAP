package com.example.employeesoap.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final String KEY_STATUS = "status";
    private static final String UNAUTHORIZED_MESSAGE = "Unauthorized";
    private static final String KEY_ERROR = "error";
    private static final String KEY_MESSAGE = "message";
    private static final String KEY_PATH = "path";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, //todo переносы параметров
                         AuthenticationException authException) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Map<String, Object> body = new HashMap<>();
        body.put(KEY_STATUS, HttpServletResponse.SC_UNAUTHORIZED);
        body.put(KEY_ERROR, UNAUTHORIZED_MESSAGE);
        body.put(KEY_MESSAGE, authException.getMessage());
        body.put(KEY_PATH, request.getServletPath());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), body);
    }
}
