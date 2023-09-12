package com.example.spring_boot_security.security;

import com.example.spring_boot_security.domain.Authorities;
import com.example.spring_boot_security.domain.Client;
import com.example.spring_boot_security.domain.Role;
import com.example.spring_boot_security.jpa.RoleJpaRepository;
import com.example.spring_boot_security.repo.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    private final RoleJpaRepository roleRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client
                = clientRepository.findClientByLoginWithFetchedRoleAndAuthorities(username);
        log.info("Authorized user successfully found {}", client.getLogin());
        Iterable<String> roles = client.getRoles()
                .stream()
                .map(Role::getRoleName)
                .toList();
        Collection<Authorities> authorities = roleRepository.findRolesWithFetchedAuthorities(roles)
                .stream()
                .flatMap(role -> role.getAuthorities().stream())
                .collect(Collectors.toSet());
        return new AuthorizedUser(
                client.getLogin(), client.getPassword(),
                client.getIsActive(), authorities,
                client.getIdentifier());
    }
}
