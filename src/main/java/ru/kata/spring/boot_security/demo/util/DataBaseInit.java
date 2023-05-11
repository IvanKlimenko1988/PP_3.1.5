package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;


@Component
public class DataBaseInit {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DataBaseInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role((long)1,"ROLE_ADMIN");
        Role roleUser = new Role((long)2,"ROLE_USER");

        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        User admin = new User("admin", "admin",
                1988, "admin@mail.ru", "admin", roleAdmin);
        User user = new User("user", "user",
                1988, "user@mail.ru", "user", roleUser);
        userService.addUser(admin);
        userService.addUser(user);
    }
}
