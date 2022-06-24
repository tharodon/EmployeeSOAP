package com.example.employeesoap.repository;


import com.example.employeesoap.entity.Role;
import com.example.employeesoap.type.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleName name);
}
