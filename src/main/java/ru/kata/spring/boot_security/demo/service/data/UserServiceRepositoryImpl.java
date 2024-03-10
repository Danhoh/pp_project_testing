package ru.kata.spring.boot_security.demo.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.model.entity.User;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceRepositoryImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
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
}
