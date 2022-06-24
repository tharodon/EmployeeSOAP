/* (C)2022 */
package com.example.employeesoap.service.mapper;

import static com.example.employeesoap.type.RoleName.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.employeesoap.api.RoleMapper;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.support.IntegrationTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RoleMapperImplTest extends IntegrationTest {

    private final RoleMapper roleMapper;

    private final Set<Role> roles =
            new HashSet<Role>() {
                {
                    add(new Role(null, ROLE_USER));
                    add(new Role(null, ROLE_ADMIN));
                }
            };

    @Autowired
    public RoleMapperImplTest(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Test
    void toRole() {
        Set<String> rolesStr =
                new HashSet<String>() {
                    {
                        add("user");
                        add("admin");
                    }
                };
        assertEquals(roles, roleMapper.toRole(rolesStr));
    }
}
