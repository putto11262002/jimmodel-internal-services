package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.JwtToken;

import java.util.UUID;

public interface AuthenticationService {

    JwtToken signIn(String username, String password);

    JwtToken refresh(String refreshToken);

    void signOut();
}
