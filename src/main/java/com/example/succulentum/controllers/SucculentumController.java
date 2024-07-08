package com.example.succulentum.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SucculentumController {
    @GetMapping("/")
    public String index(
    ) {
        return "redirect:/collection";
    }
}
