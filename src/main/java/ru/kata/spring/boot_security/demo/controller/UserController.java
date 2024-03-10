package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import java.security.Principal;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(
            @Qualifier("userServiceRepositoryImpl") UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String userPage(
            Model model,
            Principal principal
    ) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "user";
    }


}
