package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String helloPage() {
        return "/hello";
    }


    @GetMapping("/goodbye")
    public String goodByePage() {
        return "goodbye";
    }
}
