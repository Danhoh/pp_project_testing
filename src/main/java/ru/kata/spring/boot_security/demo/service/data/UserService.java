package ru.kata.spring.boot_security.demo.service.data;


import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.entities.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    void merge(User persistedUser, User newUser);

    void removeById(long id);

    User findById(long id);

    User findByUsername(String username);

    User createUserFromDto(UserDto userDto);
}
