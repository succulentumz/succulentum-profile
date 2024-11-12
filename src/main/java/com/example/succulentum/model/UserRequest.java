package com.example.succulentum.model;

public record UserRequest(String email,
                          String username,
                          String password,
                          String firstName,
                          String lastName
) {
}
