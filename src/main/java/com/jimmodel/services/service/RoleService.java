package com.jimmodel.services.service;


import com.jimmodel.services.domain.ERole;
import com.jimmodel.services.domain.Role;

public interface RoleService {
    Role findByName(ERole name);
    Role save(Role role);
}
