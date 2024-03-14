package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.kata.spring.boot_security.demo.model.entity.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import java.security.Principal;

@ControllerAdvice
public class GlobalAttributes {
    private final UserService userService;
    private final User superuser;

    @Autowired
    public GlobalAttributes(UserService userService, User superuser) {
        this.userService = userService;
        this.superuser = superuser;
    }

    @ModelAttribute
    public void getPrincipal(
            Model model,
            Principal principal
    ) {
        User user = superuser;
        try {
            user = userService.findByUsername(principal.getName());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        model.addAttribute("principal", user);
    }
}
