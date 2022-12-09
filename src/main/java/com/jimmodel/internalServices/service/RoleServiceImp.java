package com.jimmodel.internalServices.service;

import com.jimmodel.internalServices.exception.ConstraintViolationException;
import com.jimmodel.internalServices.exception.ResourceNotFoundException;
import com.jimmodel.internalServices.model.ERole;
import com.jimmodel.internalServices.model.Role;
import com.jimmodel.internalServices.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService{


    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role findByName(ERole name) {
        return roleRepository.findById(name).orElseThrow(()->  new ResourceNotFoundException(String.format("Role with name %s does not exist.", name)));
    }

    @Override
    public Role save(Role role) {
        if(roleRepository.existsById(role.getName())){
            throw new ConstraintViolationException(String.format("Role with name %s already exist.", role.getName()));
        }
        return roleRepository.save(role);
    }
}
