package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.CreateUserDto;
import ru.kata.spring.boot_security.demo.dto.ReadUserDto;
import ru.kata.spring.boot_security.demo.dto.UpdateUserDto;
import ru.kata.spring.boot_security.demo.model.entity.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import javax.persistence.EntityNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    List<ReadUserDto> getAllUsers(Principal principal) {
        return userService.getAllUsers()
                .stream()
                .map(ReadUserDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/user/add")
    public ResponseEntity<String> addUser(@RequestBody CreateUserDto createUserDto) {
        try {
            userService.save(createUserDto.createUser());
            return ResponseEntity.ok("User saved");
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Incorrect data");
        }
    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            userService.removeById(id);
            return ResponseEntity.ok("User deleted");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PutMapping("/user")
    ResponseEntity<String> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        try {
            User user = userService.findById(updateUserDto.getId());
            user.merge(updateUserDto);
            userService.save(user);
            return ResponseEntity.ok("ok");

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body("Incorrect data");
        }
    }
}
