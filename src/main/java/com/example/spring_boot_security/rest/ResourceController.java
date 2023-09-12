package com.example.spring_boot_security.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('READ')")
    ResponseEntity<String> getWithReadAuthorities() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("read successful " + authentication.getPrincipal());
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('WRITE')")
    ResponseEntity<String> getWithWriteAuthorities() {
        return ResponseEntity.ok("write successful");
    }

    @PatchMapping
    ResponseEntity<String> health() {
        return ResponseEntity.ok("ok");
    }
}
