package ru.kata.spring.boot_security.demo.model.entity;

public enum Role {
    MANAGER("Manager"),
    PROGRAMMER("Programmer"),
    CEO("CEO"),
    CONSULTANT("Consultant"),

    ADMIN("Admin"),
    USER("User");

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
}
