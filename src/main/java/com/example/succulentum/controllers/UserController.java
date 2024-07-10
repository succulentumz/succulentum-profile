package com.example.succulentum.controllers;

import com.example.succulentum.models.User;
import com.example.succulentum.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/new-user")
    public String newUser(@RequestBody User user) {
         userService.saveUser(user);
         return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
