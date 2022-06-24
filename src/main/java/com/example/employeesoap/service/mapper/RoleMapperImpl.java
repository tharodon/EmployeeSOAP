/* (C)2022 */
package com.example.employeesoap.service.mapper;

import com.example.employeesoap.api.RoleMapper;
import com.example.employeesoap.entity.Role;
import com.example.employeesoap.repository.RoleRepository;
import com.example.employeesoap.type.RoleName;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
