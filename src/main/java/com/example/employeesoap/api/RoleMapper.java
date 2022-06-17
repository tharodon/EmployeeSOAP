package com.example.employeesoap.api;

import com.example.employeesoap.entity.Role;

import java.util.Set;

public interface RoleMapper {
    Set<Role> toRole(Set<String> roles);
}
