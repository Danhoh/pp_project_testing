package ru.kata.spring.boot_security.demo.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dto.CreateUserDto;
import ru.kata.spring.boot_security.demo.dto.UserDto;
import ru.kata.spring.boot_security.demo.model.entities.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void merge(User persistedUser, User newUser) {
        if (!newUser.getFirstName().isEmpty()) {
            persistedUser.setFirstName(newUser.getFirstName());
        }

        if (!newUser.getLastName().isEmpty()) {
            persistedUser.setLastName(newUser.getLastName());
        }

        if (newUser.getAge() != null) {
            persistedUser.setAge(newUser.getAge());
        }

        if (!newUser.getUsername().isEmpty()) {
            persistedUser.setUsername(newUser.getUsername());
        }

        if (!newUser.getRoles().isEmpty()) {
            persistedUser.setRoles(newUser.getRoles());
        }

        if (!newUser.getPassword().isEmpty()) {
            persistedUser.setPassword(newUser.getPassword());
            save(persistedUser);
        } else {
            userRepository.save(persistedUser);
        }
    }

    @Override
    @Transactional
    public void removeById(long id) {
        User user = findById(id);
        userRepository.delete(user);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User createUserFromDto(UserDto userDto) {
        return new User(userDto.getUsername(), userDto.getPassword(), userDto.getFirstName(),
                userDto.getLastName(), userDto.getAge(), roleService.findRolesByStrings(userDto.getRoles()));
    }
}
