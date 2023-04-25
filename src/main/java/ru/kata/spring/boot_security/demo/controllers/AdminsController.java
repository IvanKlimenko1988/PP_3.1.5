package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UsersDetailsService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;

    @Autowired
    public AdminsController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/add")
    public String getUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/user_add";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/users")
    public String getListUsers(Model model) {
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.findById(id);
        List<Role> listRoles = userService.getRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "users/user_form";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/update")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

}