package com.example.spring_boot_security.jpa;

import com.example.spring_boot_security.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface RoleJpaRepository extends JpaRepository<Role, Long> {

    @Query("""
                from Role r
                join fetch r.authorities
                where r.roleName in :roleNames
            """)
    Collection<Role> findRolesWithFetchedAuthorities(Iterable<String> roleNames);

    Optional<Role> findRoleByRoleName(String name);
}
