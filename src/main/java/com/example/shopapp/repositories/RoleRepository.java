package com.example.shopapp.repositories;

import com.example.shopapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Boolean existsRoleByName(String name);
}
