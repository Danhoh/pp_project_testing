package ru.kata.spring.boot_security.demo.service.data;


import ru.kata.spring.boot_security.demo.model.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    void save(User user);

    void removeById(long id);

    User findById(long id);

    User findByUsername(String username);
}
