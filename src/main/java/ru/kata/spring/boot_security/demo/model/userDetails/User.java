package ru.kata.spring.boot_security.demo.model.userDetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.entity.Role;

import java.util.ArrayList;
import java.util.Collection;

public class User implements UserDetails {
    private final String username;
    private final String password;
    ArrayList<GrantedAuthority> authorities;

    public User(ru.kata.spring.boot_security.demo.model.entity.User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new ArrayList<>();

        for (Role role : user.getRole()) {
            authorities.add(
                    new SimpleGrantedAuthority(role.name())
            );
        }
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
