package com.example.employeesoap.type;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleName {
    ROLE_USER("user"),
    ROLE_ADMIN("admin");

    private final String title;

    public static RoleName getRoleName(String role) {
        if (role.equals(ROLE_USER.title)) {
            return ROLE_USER;
        }
        if (role.equals(ROLE_ADMIN.title)) {
            return ROLE_ADMIN;
        }
        return null;
    }
}
