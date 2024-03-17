package ru.kata.spring.boot_security.demo.model.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.dto.UpdateUserDto;
import ru.kata.spring.boot_security.demo.model.entity.validation.CreateValidation;
import ru.kata.spring.boot_security.demo.model.entity.validation.UpdateValidation;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
public class User implements UserDetails {
    @OneToOne(cascade = CascadeType.ALL)
    Roles roles;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty(groups = {CreateValidation.class}, message = "Email should be not empty")
    @Email(groups = {CreateValidation.class, UpdateValidation.class})
    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 20, message = "Email should be in range 3 and 20 characters")
    private String username;
    @NotEmpty(groups = {CreateValidation.class}, message = "Password should be not empty")
    @Column(columnDefinition = "TEXT")
    private String password;
    @NotEmpty(groups = {CreateValidation.class}, message = "First name should be not empty")
    @Pattern(groups = {CreateValidation.class, UpdateValidation.class}, regexp = "[A-Za-z]+", message = "Should be valid first name")
    @Size(groups = {CreateValidation.class, UpdateValidation.class}, min = 2, max = 20, message = "Firstname should be in range 2 and 20 characters")
    private String firstName;
    @NotEmpty(groups = {CreateValidation.class}, message = "Last name should be not empty")
    @Pattern(groups = {CreateValidation.class, UpdateValidation.class}, regexp = "[A-Za-z]+", message = "Should be valid last name")
    @Size(groups = {CreateValidation.class, UpdateValidation.class}, min = 2, max = 20, message = "Last name should be in range 2 and 20 characters")
    private String lastName;
    @NotNull(groups = {CreateValidation.class}, message = "Age should not be empty")
    private Integer age;


    public User() {
        this.roles = new Roles();
    }

    public User(
            String username,
            String password,
            String firstName,
            String lastName,
            Integer age,
            Roles roles
    ) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.getRoles();
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

    public void setUsername(String username) {
        this.username = username;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='" + password + '\'' +
               ", firstName='" + firstName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", roles=" + roles +
               ", age=" + age +
               '}';
    }

    public void merge(User user) {
        if (!user.getFirstName().isEmpty()) {
            this.setFirstName(user.getFirstName());
        }

        if (!user.getLastName().isEmpty()) {
            this.setLastName(user.getLastName());
        }

        if (user.getAge() != null) {
            this.setAge(user.getAge());
        }

        if (!user.getUsername().isEmpty()) {
            this.setUsername(user.getUsername());
        }

        if (!user.getRoles().getRoles().isEmpty()) {
            this.setRoles(user.getRoles());
        }

        if (!user.getPassword().isEmpty()) {
            this.setPassword(user.getPassword());
        }
    }

    public void merge(UpdateUserDto updateUserDto) {
        if (!updateUserDto.getFirstName().isEmpty()) {
            this.setFirstName(updateUserDto.getFirstName());
        }

        if (!updateUserDto.getLastName().isEmpty()) {
            this.setLastName(updateUserDto.getLastName());
        }

        if (updateUserDto.getAge() != null) {
            this.setAge(updateUserDto.getAge());
        }

        if (!updateUserDto.getUsername().isEmpty()) {
            this.setUsername(updateUserDto.getUsername());
        }

        if (!updateUserDto.getRoles().isEmpty()) {
            this.setRoles(
                    new Roles(updateUserDto.getRoles()
                            .stream()
                            .map(Role::valueOf)
                            .collect(Collectors.toList()))
            );
        }

        if (!updateUserDto.getPassword().isEmpty()) {
            this.setPassword(updateUserDto.getPassword());
        }
    }
}
