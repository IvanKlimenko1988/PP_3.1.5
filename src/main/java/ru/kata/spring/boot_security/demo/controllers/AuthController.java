package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserRegistrationService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRegistrationService userRegistrationService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserRegistrationService userRegistrationService, UserValidator userValidator) {
        this.userRegistrationService = userRegistrationService;
        this.userValidator = userValidator;
    }


    @GetMapping("/login")
    public String getLoginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String getRegistrationPage() {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String saveRegistrationForm(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "auth/registration";
        }
        userRegistrationService.registrationUser(user);
        return "redirect:auth/login";
    }
}
