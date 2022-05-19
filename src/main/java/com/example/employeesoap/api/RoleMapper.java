package com.example.employeesoap.api;

import com.example.employeesoap.entity.Role;

import java.util.Set;

//todo не нравится, что в названии есть string. Можно назвать toRole
// done
public interface RoleMapper {
    Set<Role> toRole(Set<String> roles);
}
