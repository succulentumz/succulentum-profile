package com.example.succulentum.dto.user;

public record UserRegistrationRequest(String email,
                                      String username,
                                      String password,
                                      String firstName,
                                      String lastName
) {
}
