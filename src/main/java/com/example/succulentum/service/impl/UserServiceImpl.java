package com.example.succulentum.service.impl;

import com.example.succulentum.model.NewUser;
import com.example.succulentum.service.UserService;
import com.example.succulentum.service.exceptions.UserNotCreatedException;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${app.keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    @Override
    public void createUser(NewUser newUser) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);

        userRepresentation.setEmail(newUser.email());
        userRepresentation.setUsername(newUser.username());
        userRepresentation.setFirstName(newUser.firstName());
        userRepresentation.setLastName(newUser.lastName());

        //When we obtain the smtp need to change this
//        userRepresentation.setEmailVerified(false);
        userRepresentation.setEmailVerified(true);

        CredentialRepresentation credentialRepresentation  = new CredentialRepresentation();

        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(newUser.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = getUsersResource();
        Response response = usersResource.create(userRepresentation);

        log.info("Status Code: {}", response.getStatus());
        if (!Objects.equals(201, response.getStatus())) {
            throw new UserNotCreatedException("Status code from auth server: " + response.getStatus());
        }
        log.info("New user has been created");
    }

    @Override
    public void sendVerificationEmail(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    @Override
    public void deleteUser(String userId) {
        UsersResource usersResource = getUsersResource();
        usersResource.delete(userId);
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}
