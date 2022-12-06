package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.JwtToken;
import com.jimmodel.internalServices.model.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends CrudService<User, UUID>, UserDetailsService {
    public JwtToken signIn(String username, String password) throws AuthenticationException;
}
