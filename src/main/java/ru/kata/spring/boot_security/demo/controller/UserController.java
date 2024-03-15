package ru.kata.spring.boot_security.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.model.entity.User;


@Controller
public class UserController {

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
