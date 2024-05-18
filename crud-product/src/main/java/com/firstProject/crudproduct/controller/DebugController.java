package com.firstProject.crudproduct.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DebugController {

    @GetMapping("/debug")
    public String debug() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "Authenticated user: " + authentication.getName() + ", Roles: " + authentication.getAuthorities();
    }
}
