package com.example.succulentum.controller;

import com.example.succulentum.dto.user.UserRegistrationRequest;
import com.example.succulentum.dto.user.UserResponse;
import com.example.succulentum.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @RequestBody UserRegistrationRequest newUser,
            UriComponentsBuilder uriBuilder
    ) {
        UserResponse userResponse = userService.createUser(newUser);
        return ResponseEntity
                .created(uriBuilder
                        .path("/api/users/{userId}")
                        .build(Map.of("userId", userResponse.id()))
                )
                .contentType(MediaType.APPLICATION_JSON)
                .body(userResponse);
    }

    @PutMapping("/{id}/send-verification-email")
    public ResponseEntity<?> sendVerificationEmail(
            @PathVariable String id
    ) {
        userService.sendVerificationEmail(id);
        return ResponseEntity
                .ok()
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(
            @PathVariable String id
    ) {
        userService.deleteUser(id);
        return ResponseEntity
                .ok()
                .build();
    }
}
