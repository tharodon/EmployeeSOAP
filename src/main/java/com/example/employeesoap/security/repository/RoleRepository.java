package com.example.employeesoap.security.repository;

import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.support.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(ERole name);
}
