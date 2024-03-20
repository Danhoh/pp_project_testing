package ru.kata.spring.boot_security.demo.model.enums;

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
