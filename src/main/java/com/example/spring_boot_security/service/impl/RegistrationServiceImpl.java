package com.example.spring_boot_security.service.impl;

import com.example.spring_boot_security.domain.Client;
import com.example.spring_boot_security.domain.Role;
import com.example.spring_boot_security.exception.conflict.ClientLoginAlreadyExistException;
import com.example.spring_boot_security.repo.ClientRepository;
import com.example.spring_boot_security.repo.RoleRepository;
import com.example.spring_boot_security.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Component
public class RegistrationServiceImpl implements RegistrationService {

    private final ClientRepository clientRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Client register(Client client) {
        boolean existByLogin = clientRepository.existByLogin(client.getLogin());
        if (existByLogin) {
            throw new ClientLoginAlreadyExistException(client.getLogin());
        }
        client.setIdentifier(UUID.randomUUID());
        client.setIsActive(true);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRoles(Stream.generate(this::getUserRole)
                .limit(1)
                .collect(Collectors.toSet()));
        client = clientRepository.save(client);
        return client;
    }

    private Role getUserRole() {
        return roleRepository.findRoleByName("USER");
    }
}
