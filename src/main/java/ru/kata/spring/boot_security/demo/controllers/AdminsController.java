package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserRegistrationService;
import ru.kata.spring.boot_security.demo.services.UsersDetailsService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UsersDetailsService usersDetailsService;
    private final UserRegistrationService userRegistrationService;

    @Autowired
    public AdminsController(UsersDetailsService usersDetailsService, UserRegistrationService userRegistrationService) {
        this.usersDetailsService = usersDetailsService;
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/users")
    public String getListUsers(Model model) {
        List<User> listUsers = usersDetailsService.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = usersDetailsService.findById(id);
        List<Role> listRoles = usersDetailsService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "users/user_form";
    }
    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        usersDetailsService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/update")
    public String saveUser(User user) {
        userRegistrationService.update(user);
        return "redirect:/admin/users";
    }

}