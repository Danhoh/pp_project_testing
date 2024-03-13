package ru.kata.spring.boot_security.demo.model.entity;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private List<Role> roles;

    public Roles() {
        roles = new ArrayList<>();
    }

    public Roles(List<Role> roles) {
        this.roles = roles;
    }

    public Roles(Role role) {
        roles = new ArrayList<>();
        roles.add(role);
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean hasRole(String roleStr) {
        return getRoles()
                .stream()
                .map(Role::getView)
                .collect(Collectors.toList())
                .contains(roleStr);
    }

    @Override
    public String toString() {
        return getRoles()
                .stream()
                .map(Role::getView)
                .collect(Collectors.joining(" "));
    }
}
