package com.jimmodel.internalServices.service;


import com.jimmodel.internalServices.model.Role;

public interface RoleService {

    public void init();
    public Role findByName(String name);
}
