package com.example.employeesoap.security.api;

import com.example.employeesoap.security.entity.Role;

import java.util.Set;

public interface RoleMapper {
    Set<Role> stringToRole(Set<String> roles); //todo не нравится, что в названии есть string. Можно назвать toRole
}
