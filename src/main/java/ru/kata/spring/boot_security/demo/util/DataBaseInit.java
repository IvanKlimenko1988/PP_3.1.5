package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;

@Component
public class DataBaseInit {

    private final UserService userService;

    @Autowired
    public DataBaseInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private void postConstruct() {
        User admin = new User("admin", "admin",
                1988, "admin@mail.ru", "admin",
                new Role("ROLE_ADMIN"));
        User user = new User("user", "user",
                1988, "user@mail.ru", "user",
                new Role("ROLE_USER"));
        userService.addUser(admin);
        userService.addUser(user);
    }
}
