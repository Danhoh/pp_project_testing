package ru.kata.spring.boot_security.demo.service.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.entities.Role;
import ru.kata.spring.boot_security.demo.model.enums.RoleEnum;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void saveAll(Role... roles) {
        roleRepository.saveAll(Arrays.asList(roles));
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleByName(RoleEnum roleEnum) {
        return roleRepository.findRoleByName(roleEnum).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Role findRoleByString(String roleStr) {
        return roleRepository.findRoleByName(RoleEnum.valueOf(roleStr)).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Role> findRolesByStrings(List<String> roleStrings) {
        return roleStrings.stream().map(this::findRoleByString).collect(Collectors.toList());
    }
}
