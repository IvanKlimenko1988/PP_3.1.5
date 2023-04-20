package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserRegistrationService;
import ru.kata.spring.boot_security.demo.services.UsersDetailsService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminsController {

    private final UsersDetailsService usersDetailsService;
    private final UserRegistrationService userRegistrationService;

    private final UserValidator userValidator;

    @Autowired
    public AdminsController(UsersDetailsService usersDetailsService, UserRegistrationService userRegistrationService, UserValidator userValidator) {
        this.usersDetailsService = usersDetailsService;
        this.userRegistrationService = userRegistrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping("/add")
    public String getUserForm() {
        return "users/user_add";
    }

    @PostMapping("/addForm")
    public String saveUserForm(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users/user_add";
        }
        usersDetailsService.addUser(user);
        return "redirect:auth/login";
    }
//    @PostMapping("/addForm")
//    public String saveRegistrationForm(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        userValidator.validate(user, bindingResult);
//        if (bindingResult.hasErrors()) {
//            return "users/user_add";
//        }
//        userRegistrationService.registrationUser(user);
//        return "redirect:/admin";
//    }

    @GetMapping("/users")
    public String getListUsers(Model model) {
        List<User> listUsers = usersDetailsService.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users/users";
    }

    @GetMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = usersDetailsService.findById(id);
        List<Role> listRoles = usersDetailsService.getRoles();
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