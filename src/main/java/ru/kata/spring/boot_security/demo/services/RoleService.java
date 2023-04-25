package ru.kata.spring.boot_security.demo.services;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

@Component
public interface RoleService {
    List<Role> getAllRoles();

}
