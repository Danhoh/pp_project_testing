package ru.kata.spring.boot_security.demo.service.data;

import ru.kata.spring.boot_security.demo.model.entities.Role;
import ru.kata.spring.boot_security.demo.model.enums.RoleEnum;

import java.util.List;

public interface RoleService {
    void save(Role user);

    void saveAll(Role ...roles);

    List<Role> getAllRoles();

    Role findRoleByName(RoleEnum roleEnum);

    Role findRoleByString(String roleStr);

    List<Role> findRolesByStrings(List<String> roleStrings);
}
