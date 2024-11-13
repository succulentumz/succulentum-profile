package com.example.succulentum.mapper;

import com.example.succulentum.dto.user.UserRegistrationRequest;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public class UserMapper {
    public static UserRepresentation toUserRepresentation(UserRegistrationRequest userRegistrationRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userRegistrationRequest.email());
        userRepresentation.setUsername(userRegistrationRequest.username());
        userRepresentation.setFirstName(userRegistrationRequest.firstName());
        userRepresentation.setLastName(userRegistrationRequest.lastName());

        userRepresentation.setEmailVerified(true); //TODO: Change this, when SMTP obtained

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
        credentialRepresentation.setValue(userRegistrationRequest.password());

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        return userRepresentation;
    }
}
