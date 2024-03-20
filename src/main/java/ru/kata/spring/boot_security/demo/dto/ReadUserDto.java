package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.model.entities.User;

import java.util.List;
import java.util.stream.Collectors;

public class ReadUserDto implements UserDto {
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
        this.roles = user.getRoles().stream().map(role -> role.getName().getView()).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public Integer getAge() {
        return age;
    }

    @Override
    public List<String> getRoles() {
        return roles;
    }
}
