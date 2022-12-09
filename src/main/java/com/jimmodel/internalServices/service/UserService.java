package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.JwtToken;
import com.jimmodel.internalServices.model.Role;
import com.jimmodel.internalServices.model.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;
import java.util.UUID;

public interface UserService extends CrudService<User, UUID>, UserDetailsService {
    JwtToken signIn(String username, String password) throws AuthenticationException;

    void changePassword(UUID id, String newPassword);

    void changeUsername(UUID id, String username);

    void changeUserRole(UUID id, Set<Role> newRoles);
}
