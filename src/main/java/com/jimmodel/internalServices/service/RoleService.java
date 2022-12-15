package com.jimmodel.internalServices.service;


import com.jimmodel.internalServices.domain.ERole;
import com.jimmodel.internalServices.domain.Role;

public interface RoleService {
    Role findByName(ERole name);
    Role save(Role role);
}
