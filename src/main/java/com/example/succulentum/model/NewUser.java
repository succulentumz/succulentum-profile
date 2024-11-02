package com.example.succulentum.model;

public record NewUser(String email,
                      String username,
                      String password,
                      String firstName,
                      String lastName
) {
}
