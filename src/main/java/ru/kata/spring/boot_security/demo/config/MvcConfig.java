package ru.kata.spring.boot_security.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.kata.spring.boot_security.demo.model.entity.Role;
import ru.kata.spring.boot_security.demo.model.entity.Roles;
import ru.kata.spring.boot_security.demo.model.entity.User;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/").setViewName("index");
    }

    @Bean
    public User superUser() { // представление для in-memory админа

        return new User(
                "ADMIN",
                "ADMIN",
                "ADMIN",
                new Roles(Role.ROLE_ADMIN)
        );
    }
}
