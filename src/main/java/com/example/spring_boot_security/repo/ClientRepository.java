package com.example.spring_boot_security.repo;

import com.example.spring_boot_security.domain.Client;

public interface ClientRepository {

    Client findClientByLoginWithFetchedRoleAndAuthorities(String login);

    boolean existByLogin(String login);

    Client save(Client client);
}
