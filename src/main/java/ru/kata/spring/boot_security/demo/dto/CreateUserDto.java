package ru.kata.spring.boot_security.demo.dto;

import ru.kata.spring.boot_security.demo.dto.validation.CreateValidation;
import ru.kata.spring.boot_security.demo.dto.validation.UpdateValidation;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateUserDto implements UserDto {
    @NotEmpty(groups = {CreateValidation.class}, message = "Email should be not empty")
    @Email(groups = {CreateValidation.class})
    @Size(groups = {CreateValidation.class}, min = 3, max = 20, message = "Email should be in range 3 and 20 characters")
    private String username;

    @NotEmpty(groups = {CreateValidation.class}, message = "Password should be not empty")
    @Min(value = 3, message = "Password should be bigger then 2")
    private String password;

    @NotEmpty(groups = {CreateValidation.class}, message = "First name should be not empty")
    @Pattern(groups = {CreateValidation.class}, regexp = "[A-Za-z]+", message = "Should be valid first name")
    @Size(groups = {CreateValidation.class}, min = 2, max = 20, message = "Firstname should be in range 2 and 20 characters")
    private String firstName;

    @NotEmpty(groups = {CreateValidation.class}, message = "Last name should be not empty")
    @Pattern(groups = {CreateValidation.class}, regexp = "[A-Za-z]+", message = "Should be valid last name")
    @Size(groups = {CreateValidation.class}, min = 2, max = 20, message = "Last name should be in range 2 and 20 characters")
    private String lastName;

    @NotEmpty(groups = {CreateValidation.class}, message = "Age should not be empty")
    @Min(value = 10, message = "Should be greater then 9")
    @Max(value = 99, message = "Should be less then 100")
    private Integer age;

    private List<String> roles;

    public CreateUserDto() {

    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "CreateUserDto{" + "username='" + username + '\'' + ", password='" + password + '\'' + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", age=" + age + ", roles=" + roles + '}';
    }
}
