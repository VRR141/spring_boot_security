package com.example.spring_boot_security.repo.impl;

import com.example.spring_boot_security.domain.Role;
import com.example.spring_boot_security.exception.not_found.RoleNotFoundException;
import com.example.spring_boot_security.jpa.RoleJpaRepository;
import com.example.spring_boot_security.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;

@RequiredArgsConstructor
@Component
public class RoleRepositoryImpl implements RoleRepository {

    private final RoleJpaRepository roleJpaRepository;

    @Override
    public Collection<Role> findRolesWithFetchedAuthorities(Iterable<String> roleNames) {
        return roleJpaRepository.findRolesWithFetchedAuthorities(roleNames);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleJpaRepository.findRoleByRoleName(name)
                .orElseThrow(() -> new RoleNotFoundException(name));
    }
}
