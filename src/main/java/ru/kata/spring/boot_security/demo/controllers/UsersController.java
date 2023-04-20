package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImpl;
import ru.kata.spring.boot_security.demo.services.UsersDetailsService;

@Controller
@RequestMapping("/user")
public class UsersController {
    private final UsersDetailsService usersDetailsService;

    @Autowired
    public UsersController(UsersDetailsService usersDetailsService) {
        this.usersDetailsService = usersDetailsService;
    }


    @GetMapping("/showUserInfo")
    public String showUser(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetailsImp = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetailsImp.getUser();
        model.addAttribute("user", user);
        return "users/user";
    }

    @GetMapping("/user-info/{id}")
    public String getUserPage(@PathVariable("id") Long id, Model model) {
        User user = usersDetailsService.findById(id);
        model.addAttribute("user", user);
        return "/users/user_info";
    }
}


