package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceRepositoryImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String get(@ModelAttribute("principle") User principal) {
        if (principal.getUsername() == null) {
            return "redirect:/login";
        } else {
            return "user";
        }
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }
}
