package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.domain.Role;
import com.jimmodel.internalServices.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;
import java.util.UUID;

public interface UserService extends CrudService<User, UUID>, UserDetailsService {

    void changePassword(UUID id, String newPassword);

    void changeUsername(UUID id, String username);

    void changeUserRole(UUID id, Set<Role> newRoles);
}
