package ru.kata.spring.boot_security.demo.service.data;


import ru.kata.spring.boot_security.demo.dto.CreateUserDto;
import ru.kata.spring.boot_security.demo.dto.UpdateUserDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.entities.Role;
import ru.kata.spring.boot_security.demo.model.entities.User;
import ru.kata.spring.boot_security.demo.model.enums.RoleEnum;

import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    void merge(User persistedUser, User newUser);

    void removeById(long id);

    User findById(long id);

    User findByUsername(String username);

    User createUserFromDto(UserDto userDto);
}
