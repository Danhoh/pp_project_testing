package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReadUserDto {
    private final long id;
    private final String username;
    private final String firstName;
    private final String lastName;

    private final Integer age;

    private final List<String> roles;

    public ReadUserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.age = user.getAge();
        this.roles = user.getRoles().getRoles()
                .stream()
                .map(Role::getView)
                .collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<String> getRoles() {
        return roles;
    }
}
