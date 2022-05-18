package com.example.employeesoap.security.service;

import com.example.employeesoap.security.api.RoleMapper;
import com.example.employeesoap.security.entity.Role;
import com.example.employeesoap.security.repository.RoleRepository;
import com.example.employeesoap.security.type.ERole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {
    private final RoleRepository roleRepository;

    @Override
    public Set<Role> stringToRole(Set<String> roles) {
        return roles.stream()
                .map(role -> roleRepository.findRoleByName(
                                ERole.getERole(role))
                        .orElseThrow(RuntimeException::new))
                .collect(Collectors.toSet());
    }
}
