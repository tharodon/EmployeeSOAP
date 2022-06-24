package com.example.employeesoap.controller;


import io.swagger.v3.oas.annotations.Hidden;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Hidden
public class InfoController {
    @SneakyThrows
    @GetMapping("/info")
    public void redirectSwagger(HttpServletResponse response) {
        response.sendRedirect("/server/swagger-ui.html");
    }
}
