package ru.kata.spring.boot_security.demo.model.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchProfile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.entity.Role;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3, max = 20, message = "Username should be in range 3 and 20 characters")
    private String username;

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 3, max = 20, message = "Password should be in range 3 and 20 characters")
    private String password;
    @NotEmpty(message = "First name should be not empty")
    @Pattern(regexp = "[A-Za-z]+", message = "Should be valid first name")
    @Size(min = 2, max = 20, message = "Firstname should be in range 2 and 20 characters")
    private String firstName;
    @NotEmpty(message = "Last name should be not empty")
    @Pattern(regexp = "[A-Za-z]+", message = "Should be valid last name")
    @Size(min = 2, max = 20, message = "Last name should be in range 2 and 20 characters")
    private String lastName;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "enum_mapping_table", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "your_enum_column")
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    public User() {

    }

    public User(
            String username,
            String firstName,
            String lastName,
            ArrayList<Role> role
    ) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRole();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
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
