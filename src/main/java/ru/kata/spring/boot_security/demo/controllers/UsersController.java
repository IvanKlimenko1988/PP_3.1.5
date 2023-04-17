package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.security.UserDetailsImp;
import ru.kata.spring.boot_security.demo.service.UserDetailsService;

import java.util.Collection;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UsersController {

    @GetMapping("/showUserInfo")
    public String showUserInfo() {


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

//        UserDetailsService userDetailsService = (UserDetailsService) authentication.getPrincipal();
//        User user = userDetailsService.loadUserByUsername(authentication.)
//        UserDetailsImp userDetailsImp = (UserDetailsImp)authentication.getPrincipal();
//        UserDetailsImp userDetailsImp = ()

        System.out.println(userDetails.getUsername() + " " + userDetails.getPassword());
//        System.out.println(userDetailsService.loadUserByUsername() + " " + userDetailsImp.getUser().getEmail());
        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin";
    }


//    private UserService userService;

//    @Autowired
//    public UsersController(UserService userService) {
//        this.userService = userService;
//    }



//    @GetMapping()
//    public String showAllUsers(Model model) {
//        List<User> allUsers = userService.getAllUsers();
//        model.addAttribute("allUsers", allUsers);
//        return "/users";
//    }
//
//    @GetMapping("/add")
//    public String showUserForm(Model model) {
//        model.addAttribute("user", new User());
//        return "user-info";
//    }
//
//    @PostMapping
//    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors())
//            return "user-info";
//        userService.add(user);
//        return "redirect:/users";
//    }
//
//    @GetMapping("user-delete/{id}")
//    public String deleteUser(@PathVariable("id") Long id) {
//        userService.delete(id);
//        return "redirect:/users";
//    }
//
//    @GetMapping("user-update/{id}")
//    public String getUserForm(@PathVariable("id") Long id, Model model) {
//        User user = userService.getById(id);
//        model.addAttribute("user", user);
//        return "edit";
//    }
//
//    @PostMapping("user-update")
//    public String userUpdate(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "user-info";
//        }
//        userService.edit(user);
//        System.out.println("edit");
//        return "redirect:/users";
//    }

//    @GetMapping("/authenticated")
//    public String pageForAuthenticatedUsers(Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        return "secured part of web service: " + user.getUsername() + " "
//                + user.getEmail();
//    }
}
