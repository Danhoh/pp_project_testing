package ru.kata.spring.boot_security.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import ru.kata.spring.boot_security.demo.model.entities.Role;
import ru.kata.spring.boot_security.demo.model.entities.User;
import ru.kata.spring.boot_security.demo.model.enums.RoleEnum;
import ru.kata.spring.boot_security.demo.service.data.RoleService;
import ru.kata.spring.boot_security.demo.service.data.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Configuration
public class CreateDropConfig {
    UserService userService;
    RoleService roleService;

    @Autowired
    public CreateDropConfig(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    protected void init() {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            roleService.save(new Role(roleEnum));
        }
        userService.save(new User("admin", "admin", "admin", "admin", 11,
                roleService.getAllRoles()));
        userService.save(new User("user", "user", "user", "user", 11,
                List.of(roleService.findRoleByName(RoleEnum.ROLE_USER))));
    }
}
