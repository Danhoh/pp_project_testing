package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.ReadUserDto;
import ru.kata.spring.boot_security.demo.model.entities.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/principal")
    private ReadUserDto getPrincipal(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        System.out.println(user.getAuthorities());
        return new ReadUserDto(user);
    }
}
