package ru.kata.spring.boot_security.demo.model;

public enum Role {
    MANAGER("Manager"),
    PROGRAMMER("Programmer"),
    CEO("CEO"),
    CONSULTANT("Consultant");

    private final String view;

    Role(String view) {
        this.view = view;
    }

    public String getView() {
        return this.view;
    }
}
