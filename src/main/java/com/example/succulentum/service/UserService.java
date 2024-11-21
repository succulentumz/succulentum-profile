package com.example.succulentum.service;

import com.example.succulentum.mapper.UserMapper;
import com.example.succulentum.dto.user.UserRegistrationRequest;
import com.example.succulentum.dto.user.UserResponse;
import com.example.succulentum.service.exceptions.UserNotCreatedException;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    @Value("${app.keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    public UserResponse createUser(UserRegistrationRequest newUser) {
        UserRepresentation userRepresentation = UserMapper.toUserRepresentation(newUser);
        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);

        log.info("Status Code: {}", response.getStatus());
        if (response.getStatus() != 201) {
            throw new UserNotCreatedException("Status code from auth server: " + response.getStatus());
        }

        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
        log.info("New user has been created with ID: {}", userId);

        return new UserResponse(userId, newUser.username(), newUser.email());
    }

    public void sendVerificationEmail(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}
