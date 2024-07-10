package com.example.succulentum.controllers;

import com.example.succulentum.models.User;
import com.example.succulentum.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/new-user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String newUser(@RequestBody User user) {
         userService.saveUser(user);
         log.info("saved user: {}", user);
         return "redirect:/";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
