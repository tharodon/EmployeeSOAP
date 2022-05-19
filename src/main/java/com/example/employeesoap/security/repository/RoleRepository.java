package com.example.employeesoap.security.repository;
//todo не понимаю зачем еще один пакет repository

import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.type.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(ERole name);
}
