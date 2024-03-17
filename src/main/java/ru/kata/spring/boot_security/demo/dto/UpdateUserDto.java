package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.Roles;
import ru.kata.spring.boot_security.demo.model.entity.User;

import java.util.List;
import java.util.stream.Collectors;

public class UpdateUserDto {
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> roles;

    public UpdateUserDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
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

    public User createUser() {
        return new User(
                getUsername(),
                getPassword(),
                getFirstName(),
                getLastName(),
                getAge(),
                new Roles(getRoles().stream().map(Role::valueOf).collect(Collectors.toList()))
        );
    }

    @Override
    public String toString() {
        return "UpdateUserDto{" +
               "username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", age=" + age +
               ", roles=" + roles +
               '}';
    }
}
