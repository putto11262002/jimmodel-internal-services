package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.model.Role;
import com.jimmodel.internalServices.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void init() {
        roleRepository.save(new Role(null, "Booker", "Booker"));
    }

}
