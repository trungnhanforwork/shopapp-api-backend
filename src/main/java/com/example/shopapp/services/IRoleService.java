package com.example.shopapp.services;

import com.example.shopapp.dtos.RoleDTO;
import com.example.shopapp.models.Role;
import org.springframework.stereotype.Service;

@Service
public interface IRoleService {
    Role createRole(RoleDTO roleDTO);

    Boolean existsRoleByName(String name);

}
