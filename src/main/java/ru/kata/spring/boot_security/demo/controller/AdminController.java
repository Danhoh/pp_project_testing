package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.User;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String get(Model model,
    Principal principal) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User());

        User user;

        try {
            user = userService.findByUsername(principal.getName());
        } catch (EntityNotFoundException ex) {
            user = new User();
        }

        model.addAttribute("principal", user);
        return "admin";
    }

    @GetMapping("/add")
    public String addUserPage(@ModelAttribute("user") User user) {
        return "add-user-form";
    }

    @PostMapping("/add")
    public String post(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-user-form";
        }
        userService.save(user);

        return "redirect:/admin/";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") long id) {
        userService.removeById(id);
        return "redirect:/admin/";
    }

    @GetMapping("/update")
    public String update(@RequestParam long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "add-user-form";
    }

    @ModelAttribute("roles")
    public Role[] getRoles() {
        return Role.values();
    }
}
