package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.Roles;
import ru.kata.spring.boot_security.demo.model.entity.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public class UpdateUserDto {
    private Long id;
    @Email
    @Size(min = 3, max = 20, message = "Email should be in range 3 and 20 characters")
    private String username;
    private String password;
    @Pattern(regexp = "[A-Za-z]+", message = "Should be valid first name")
    @Size(min = 2, max = 20, message = "Firstname should be in range 2 and 20 characters")
    private String firstName;
    @Pattern(regexp = "[A-Za-z]+", message = "Should be valid last name")
    @Size(min = 2, max = 20, message = "Last name should be in range 2 and 20 characters")
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
