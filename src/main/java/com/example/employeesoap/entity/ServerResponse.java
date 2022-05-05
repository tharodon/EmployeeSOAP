package com.example.employeesoap.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerResponse {
    private String error;

    public ServerResponse(String error) {
        this.error = error;
    }
}
