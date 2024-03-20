package ru.kata.spring.boot_security.demo.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String view;

    RoleEnum(String view) {
        this.view = view;
    }

    public String getView() {
        return this.view;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
