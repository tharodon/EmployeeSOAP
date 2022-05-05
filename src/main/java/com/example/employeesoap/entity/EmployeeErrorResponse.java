package com.example.employeesoap.entity;

import io.spring.guides.gs_producing_web_service.EmployeeResponse;

public class EmployeeErrorResponse extends EmployeeResponse {
    private String error;

    public EmployeeErrorResponse(String error) {
        this.error = error;
    }
}
