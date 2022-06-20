package com.example.employeesoap.service.mapper;

import com.example.employeesoap.IntegrationTest;
import com.example.employeesoap.api.RoleMapper;
import com.example.employeesoap.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static com.example.employeesoap.type.RoleName.*;
import static org.junit.jupiter.api.Assertions.*;

class RoleMapperImplTest extends IntegrationTest {

    private final RoleMapper roleMapper;

    private final Set<Role> roles = new HashSet<Role>(){{
        add(new Role(null, ROLE_USER));
        add(new Role(null, ROLE_ADMIN));
    }};

    @Autowired
    public RoleMapperImplTest(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Test
    void toRole() {
        Set<String> rolesStr = new HashSet<String>(){{
            add("user");
            add("admin");
        }};
        assertEquals(roles, roleMapper.toRole(rolesStr));
    }
}