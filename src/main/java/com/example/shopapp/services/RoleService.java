package com.example.shopapp.services;

import com.example.shopapp.dtos.RoleDTO;
import com.example.shopapp.models.Role;
import com.example.shopapp.repositories.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RoleService implements IRoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role createRole(RoleDTO roleDTO) {
        Role newRole = Role.builder()
                .name(roleDTO.getName())
                .build();
        return roleRepository.save(newRole);
    }

    @Override
    public Boolean existsRoleByName(String name) {
        return roleRepository.existsRoleByName(name);
    }
}
