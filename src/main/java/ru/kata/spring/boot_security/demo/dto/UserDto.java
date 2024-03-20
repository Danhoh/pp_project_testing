package ru.kata.spring.boot_security.demo.dto;

import java.util.List;

public interface UserDto {
    String getUsername();
    String getPassword();
    String getFirstName();
    String getLastName();
    Integer getAge();
    List<String> getRoles();
}
