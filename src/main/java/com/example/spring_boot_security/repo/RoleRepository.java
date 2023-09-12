package com.example.spring_boot_security.repo;

import com.example.spring_boot_security.domain.Role;

import java.util.Collection;

public interface RoleRepository {

    Collection<Role> findRolesWithFetchedAuthorities(Iterable<String> roleNames);

    Role findRoleByName(String name);
}
