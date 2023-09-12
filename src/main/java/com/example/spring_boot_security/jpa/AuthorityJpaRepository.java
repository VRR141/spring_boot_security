package com.example.spring_boot_security.jpa;

import com.example.spring_boot_security.domain.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityJpaRepository extends JpaRepository<Authorities, Long> {
}
