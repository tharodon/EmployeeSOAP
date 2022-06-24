package com.example.employeesoap.repository;

import com.example.employeesoap.entity.Role;
import com.example.employeesoap.type.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleName name);
}
