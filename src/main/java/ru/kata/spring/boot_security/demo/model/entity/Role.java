package ru.kata.spring.boot_security.demo.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private final String view;

    Role(String view) {
        this.view = view;
    }

    public String getView() {
        return this.view;
    }

    @Override
    public String toString() {
        return this.name();
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
