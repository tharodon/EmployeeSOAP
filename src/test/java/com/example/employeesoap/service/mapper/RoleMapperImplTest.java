package com.example.employeesoap.service.mapper;

import com.example.employeesoap.api.RoleMapper;
import com.example.employeesoap.support.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

import static com.example.employeesoap.support.testdata.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleMapperImplTest extends IntegrationTest {

    private final RoleMapper roleMapper;

    @Autowired
    public RoleMapperImplTest(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Test
    void toRole() {
        Set<String> rolesStr =
                new HashSet<>() {
                    {
                        add(USER);
                        add(ADMIN);
                    }
                };
        assertEquals(ROLES, roleMapper.toRole(rolesStr));
    }
}
