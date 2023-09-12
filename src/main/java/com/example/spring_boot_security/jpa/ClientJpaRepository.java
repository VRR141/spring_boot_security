package com.example.spring_boot_security.jpa;

import com.example.spring_boot_security.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientJpaRepository extends JpaRepository<Client, Long> {

    @Query("""
                    from Client c
                    join fetch c.roles
                    where c.login = :login
            """)
    Optional<Client> findClientByLoginWithFetchedRolesAndAuthorities(String login);

    Boolean existsClientByLogin(String login);
}
