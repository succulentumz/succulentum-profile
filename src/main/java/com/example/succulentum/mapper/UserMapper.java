package com.example.succulentum.mapper;

import com.example.succulentum.model.UserRequest;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public class UserMapper {
    public static UserRepresentation toUserRepresentation(UserRequest userRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userRequest.email());
        userRepresentation.setUsername(userRequest.username());
        userRepresentation.setFirstName(userRequest.firstName());
        userRepresentation.setLastName(userRequest.lastName());

        userRepresentation.setEmailVerified(true); //TODO: Change this, when SMTP obtained

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        return userRepresentation;
    }
}
