package com.example.succulentum.controller;

import com.example.succulentum.model.NewUser;
import com.example.succulentum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUser newUser) {
        userService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(@PathVariable String id) {
        userService.sendVerificationEmail(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);

        return ResponseEntity.ok().build();
    }
}
