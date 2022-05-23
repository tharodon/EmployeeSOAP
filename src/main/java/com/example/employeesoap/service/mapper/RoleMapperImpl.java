package com.example.employeesoap.service.mapper;

import com.example.employeesoap.api.RoleMapper;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.repository.RoleRepository;
import com.example.employeesoap.type.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleMapperImpl implements RoleMapper {
    private final RoleRepository roleRepository;

    @Override
    public Set<Role> toRole(Set<String> roles) {
        return roles.stream()
                .map(role -> roleRepository.findRoleByName(RoleName.getRoleName(role)).get())
                .collect(Collectors.toSet());
    }
}
