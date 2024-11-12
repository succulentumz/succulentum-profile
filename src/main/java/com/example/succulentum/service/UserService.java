package com.example.succulentum.service;

import com.example.succulentum.dto.user.UserRegistrationRequest;
import com.example.succulentum.dto.user.UserResponse;

public interface UserService {
    UserResponse createUser(UserRegistrationRequest newUser);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
}
