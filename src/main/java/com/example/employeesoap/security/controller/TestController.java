package com.example.employeesoap.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TestController {

    @GetMapping("/all")
    public String getAll(){
        return "All";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String getAuth(){
        return "Authorized";
    }
}
