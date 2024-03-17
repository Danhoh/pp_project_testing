package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.ReadUserDto;
import ru.kata.spring.boot_security.demo.model.entity.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import java.security.Principal;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final User superuser;

    @Autowired
    public UserController(
            UserService userService,
            User superuser
    ) {
        this.userService = userService;
        this.superuser = superuser;
    }

    @GetMapping(path = "/principal")
    ReadUserDto getPrincipal(Principal principal) {
        if (principal != null)
            return new ReadUserDto(
                    principal.getName().equals("admin") ?
                            superuser :
                            userService.findByUsername(principal.getName())
            );
        else {
            return null;
        }
    }
}
