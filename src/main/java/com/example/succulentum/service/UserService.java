package com.example.succulentum.service;

import com.example.succulentum.model.UserRequest;
import com.example.succulentum.model.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest newUser);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
}
