package com.example.succulentum.service;

import com.example.succulentum.model.NewUser;

public interface UserService {

    void createUser(NewUser newUser);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
}
