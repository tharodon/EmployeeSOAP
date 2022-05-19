package com.example.employeesoap.security.type;

public enum ERole { //todo название
    ROLE_USER, ROLE_ADMIN;

    public static ERole getERole(String role) {
        switch (role) {
            case ("user"): { //todo добавь в поле title enum и используй
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
