package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import ru.kata.spring.boot_security.demo.dto.validation.CreateValidation;
import ru.kata.spring.boot_security.demo.model.entities.Role;
import ru.kata.spring.boot_security.demo.model.entities.User;
import ru.kata.spring.boot_security.demo.service.data.RoleService;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    private AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/user/all")
    private List<ReadUserDto> getAllUsers() {
        return userService.getAllUsers().stream().map(ReadUserDto::new).collect(Collectors.toList());
    }

    @PostMapping("/user/add")
    private ResponseEntity<?> addUser(@Validated({CreateValidation.class}) @RequestBody CreateUserDto createUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        System.out.println(createUserDto);
        userService.save(userService.createUserFromDto(createUserDto));
        return ResponseEntity.ok("User saved");
    }

    @DeleteMapping("/user/{id}")
    private ResponseEntity<String> deleteUser(@PathVariable long id) {
        try {
            userService.removeById(id);
            return ResponseEntity.ok("User deleted");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body("User not found");
        }
    }

    @PutMapping("/user")
    private ResponseEntity<?> updateUser(@Validated @RequestBody UpdateUserDto updateUserDto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }

        try {
            User user = userService.findById(updateUserDto.getId());
            userService.merge(user, userService.createUserFromDto(updateUserDto));
            return ResponseEntity.ok("ok");

        } catch (EntityNotFoundException ex) {
            return ResponseEntity.badRequest().body("Incorrect data");
        }
    }

    @GetMapping("/roles")
    List<String>  getRoles() {
        return roleService.getAllRoles().stream().map(role -> role.getName().name()).collect(Collectors.toList());
    }
}
