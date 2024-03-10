package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import javax.validation.Valid;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(@Qualifier("userServiceRepositoryImpl") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String get(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "admin";
    }

    @PostMapping("/add")
    public String post(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "update";
        }
        userService.save(user);

        return "redirect:/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam long id) {
        userService.removeById(id);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "update";
    }

    @ModelAttribute("roles")
    public Role[] getRoles() {
        return Role.values();
    }
}
