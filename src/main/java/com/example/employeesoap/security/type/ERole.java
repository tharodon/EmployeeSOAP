package com.example.employeesoap.security.type;

public enum ERole {
    ROLE_USER, ROLE_ADMIN;

    public static ERole getERole(String role) {
        switch (role) {
            case ("user"): {
                return ROLE_USER;
            }
            case ("admin"): {
                return ROLE_ADMIN;
            }
            default:
                return null;
        }
    }
}
