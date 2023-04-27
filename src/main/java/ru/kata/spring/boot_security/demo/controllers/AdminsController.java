package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String getAdminPage(Model model) {
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImp = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetailsImp.getUser();
        model.addAttribute("user", user);
        return "admin";
    }

    @PostMapping("/add")
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
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "admin";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @PostMapping("/edit")
    public String updateUser(User user) {
        userService.updateUser(user);
        return "redirect:/admin/users";
    }

}