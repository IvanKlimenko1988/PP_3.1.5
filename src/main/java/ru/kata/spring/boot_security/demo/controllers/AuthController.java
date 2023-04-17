package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserRegistration;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRegistration userRegistration;

    @Autowired
    public AuthController(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/user-info")
    public String getRegistrationPage(@ModelAttribute("user") User user) {
        return "/auth/user-info";
    }

    @PostMapping("/user-info")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/auth/user-info";
        }
        userRegistration.registrationUser(user);
        return "redirect:/auth/login";
    }
}
