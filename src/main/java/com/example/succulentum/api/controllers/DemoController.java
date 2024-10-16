package com.example.succulentum.api.controllers;

import com.example.succulentum.storage.repo.SucUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DemoController {
    private SucUserRepository sucUserRepository;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
