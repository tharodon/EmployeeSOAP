package com.example.employeesoap.repository;
//todo не понимаю зачем еще один пакет repository
// done

import com.example.employeesoap.entity.Role;
import com.example.employeesoap.type.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(RoleName name);
}
