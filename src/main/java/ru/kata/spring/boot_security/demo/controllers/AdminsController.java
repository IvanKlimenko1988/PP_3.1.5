package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;
import java.util.List;

//@Controller
//@RequestMapping("/admin")
public class AdminsController {

    private final UserService userService;

    private final RoleService roleService;

//    @Autowired
    public AdminsController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("")
    public String getAdminPage(Model model, Principal principal) {
        List<Role> listRoles = roleService.getAllRoles();
        model.addAttribute("listRoles", listRoles);
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers", listUsers);
        User user = userService.findUserByName(principal.getName());
        model.addAttribute("user", user);
        return "admin";
    }

//    @PostMapping("/add")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/admin";
    }

//    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

//    @PatchMapping(value = "/edit/{id}")
//    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
//        userService.updateUser(user, id);
//        return "redirect:/admin";
//    }

}