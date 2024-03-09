package ru.kata.spring.boot_security.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3, max = 20, message = "Username should be in range 3 and 20 characters")
    private String username;
    @NotEmpty(message = "First name should be not empty")
    @Pattern(regexp = "[A-Za-z]+", message = "Should be valid first name")
    @Size(min = 2, max = 20, message = "Firstname should be in range 2 and 20 characters")
    private String firstName;
    @NotEmpty(message = "Last name should be not empty")
    @Pattern(regexp = "[A-Za-z]+", message = "Should be valid last name")
    @Size(min = 2, max = 20, message = "Last name should be in range 2 and 20 characters")
    private String lastName;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {

    }

    public User(
            String username,
            String firstName,
            String lastName,
            Role role
    ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", role=" + role +
               '}';
    }
}
