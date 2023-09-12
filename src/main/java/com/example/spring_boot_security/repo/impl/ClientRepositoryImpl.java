package com.example.spring_boot_security.repo.impl;

import com.example.spring_boot_security.domain.Client;
import com.example.spring_boot_security.exception.not_found.ClientNotFoundByLoginException;
import com.example.spring_boot_security.jpa.ClientJpaRepository;
import com.example.spring_boot_security.repo.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {

    private final ClientJpaRepository clientJpaRepository;

    @Override
    public Client findClientByLoginWithFetchedRoleAndAuthorities(String login) {
        return clientJpaRepository.findClientByLoginWithFetchedRolesAndAuthorities(login)
                .orElseThrow(() -> new ClientNotFoundByLoginException(login));
    }

    @Override
    public boolean existByLogin(String login) {
        return clientJpaRepository.existsClientByLogin(login);
    }

    @Override
    public Client save(Client client) {
        return clientJpaRepository.save(client);
    }
}
