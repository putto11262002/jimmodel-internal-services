package com.jimmodel.services.service;

import com.jimmodel.services.domain.JwtToken;

public interface AuthenticationService {

    JwtToken signIn(String username, String password);

    JwtToken refresh(String refreshToken);

    void signOut();
}
