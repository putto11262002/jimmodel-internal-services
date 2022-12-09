package com.jimmodel.internalServices.service;


import com.jimmodel.internalServices.model.ERole;
import com.jimmodel.internalServices.model.Role;

public interface RoleService {
    Role findByName(ERole name);
    Role save(Role role);
}
